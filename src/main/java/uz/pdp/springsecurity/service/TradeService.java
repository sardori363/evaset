package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductTradeDto;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    PaymentStatusRepository paymentStatusRepository;

    @Autowired
    PayMethodRepository payMethodRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TradeProductRepository tradeProductRepository;

    @Autowired
    TradeHistoryRepository tradeHistoryRepository;

    public ApiResponse getAll() {
        List<Trade> all = tradeRepository.findAll();
        return new ApiResponse(true, all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Trade> optionalTrade = tradeRepository.findById(id);
        if (optionalTrade.isPresent()) {
            return new ApiResponse(true, optionalTrade.get());
        }
        return new ApiResponse("NOT FOUND", false);
    }

    public ApiResponse create(TradeDTO tradeDTO) {
        Trade trade = new Trade();
        ApiResponse apiResponse = addTraade(trade, tradeDTO);

        return apiResponse;
    }

    public ApiResponse deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
        return new ApiResponse("DELATED", true);
    }

    public ApiResponse edit(Integer id, TradeDTO tradeDTO) {
        Optional<Trade> optionalTrade = tradeRepository.findById(id);
        if (!optionalTrade.isPresent()) {
            return new ApiResponse("NOT FOUND TRADE", false);
        }
        Trade trade = optionalTrade.get();
        ApiResponse apiResponse = addTraade(trade, tradeDTO);

        if (!apiResponse.isSuccess()) {
            return new ApiResponse("ERROR", false);
        }
        return new ApiResponse("UPDATED", true);
    }


    public ApiResponse addTraade(Trade trade, TradeDTO tradeDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(tradeDTO.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            return new ApiResponse("CUSTOMER NOT FOUND", false);
        }
        trade.setCustomer(optionalCustomer.get());

        /**
         * SOTUVCHI SAQLANDI
         */
        Optional<User> optionalUser = userRepository.findById(tradeDTO.getUserId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("TRADER NOT FOUND", false);
        }
        trade.setTrader(optionalUser.get());


        /**
         * SOTILGAN PRODUCT SAQLANDI YANI TRADERPRODUCT
         */
        List<ProductTradeDto> productTraderDto = tradeDTO.getProductTraderDto();
        List<TradeProduct> tradeProducts = new ArrayList<>();
        for (ProductTradeDto productTradeDto : productTraderDto) {

            Integer tradedQuantity = productTradeDto.getTradedQuantity();
            Optional<Product> optionalProduct = productRepository.findById(productTradeDto.getProductTradeId());
            Product product = optionalProduct.get();

            if (tradedQuantity > product.getQuantity()) {
                return new ApiResponse("OMBORDA YETARLICHA MAXSULOT YO'Q", false);
            }
            product.setQuantity(product.getQuantity() - tradedQuantity);
            productRepository.save(product);

            TradeProduct tradeProduct = new TradeProduct();
            tradeProduct.setTradedQuantity(productTradeDto.getTradedQuantity());
            tradeProduct.setProduct(product);

            tradeProducts.add(tradeProduct);

            tradeProductRepository.save(tradeProduct);
        }
        /**
         * SOTILGAN PRODUCT SAQLANDI YANI TRADERPRODUCT
         */
        trade.setTradeProductList(tradeProducts);


        /**
         * UMUMIY SUMMA SAQLANDI
         */
        double sum = 0d;
        for (ProductTradeDto productTradeDto : productTraderDto) {
            double salePrice = productRepository.findById(productTradeDto.getProductTradeId()).get().getSalePrice();
            Integer tradedQuantity = productTradeDto.getTradedQuantity();
            double parseDouble = Double.parseDouble(String.valueOf(tradedQuantity));
            sum = sum + (salePrice * parseDouble);
        }
        trade.setTotalSum(sum);

        /**
         * AMOUNT LOAN PRICE SAQLANDI
         */
        if (tradeDTO.getAmountPaid() == 0d) {

            trade.setAmountPaid(0.0);
            trade.setLoan(sum - tradeDTO.getAmountPaid());

            Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(3);
            PaymentStatus paymentStatus = optionalPaymentStatus.get();

            trade.setPaymentStatus(paymentStatus);
        } else if (sum < tradeDTO.getAmountPaid()) {
            return new ApiResponse("A LOT OF MONEY PAID", false);
        } else {
            if (trade.getAmountPaid()!=null) {
                if ((trade.getTotalSum() < (trade.getAmountPaid() + tradeDTO.getAmountPaid()))) {
                    return new ApiResponse("A LOT OF MONEY PAID", false);
                }else {
                    trade.setAmountPaid(trade.getAmountPaid() + tradeDTO.getAmountPaid());
                    trade.setLoan(trade.getLoan() - tradeDTO.getAmountPaid());
                }
            } else if (trade.getAmountPaid() == null || trade.getAmountPaid() == 0.0) {
                trade.setAmountPaid(tradeDTO.getAmountPaid());
                trade.setLoan(sum - tradeDTO.getAmountPaid());
            } else {
                trade.setAmountPaid(trade.getAmountPaid() + tradeDTO.getAmountPaid());
                trade.setLoan(trade.getLoan() - tradeDTO.getAmountPaid());
            }

            if ((trade.getTotalSum() - tradeDTO.getAmountPaid() == 0) || trade.getLoan() == 0.0) {
                Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(1);
                PaymentStatus paymentStatus = optionalPaymentStatus.get();
                trade.setPaymentStatus(paymentStatus);
            } else {
                Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(2);
                PaymentStatus paymentStatus = optionalPaymentStatus.get();
                trade.setPaymentStatus(paymentStatus);
            }

        }


        /**
         * BRANCH SAQLANDI
         */
        Optional<Branch> optionalBranch = branchRepository.findById(tradeDTO.getBranchId());
        if (!optionalBranch.isPresent()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }
        trade.setBranch(optionalBranch.get());


        /**
         * PAYMAENTMETHOD SAQLANDI
         */
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeDTO.getPayMethodId());
        if (!optionalPaymentMethod.isPresent()) {
            return new ApiResponse("PAYMAENTMETHOD NOT FOUND", false);
        }
        trade.setPayMethod(optionalPaymentMethod.get());


        /**
         * ADDRESS SAQLANDI
         */
        Optional<Address> optionalAddress = addressRepository.findById(tradeDTO.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new ApiResponse("ADDRESS NOT FOUND", false);
        }
        trade.setAddress(optionalAddress.get());
        /**
         * PAYDATE SAQLANDI
         */
        Date payDate = tradeDTO.getPayDate();
        trade.setPayDate(payDate);
        tradeRepository.save(trade);

        /**
         * TRADE HISTORY QOSHILYAPTI
         */

        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setPaidAmount(tradeDTO.getAmountPaid());
        Date date = new Date(System.currentTimeMillis());
        tradeHistory.setPaidDate(date);
        tradeHistory.setTrade(trade);
        PaymentMethod payMethod = trade.getPayMethod();
        tradeHistory.setPaymentMethod(payMethod.getType());
        tradeHistoryRepository.save(tradeHistory);


        return new ApiResponse("SAVED!", true);
    }

    public ApiResponse getByTraderId(Integer trader_id) {
        List<Trade> allByTrader_id = tradeRepository.findAllByTrader_Id(trader_id);
        if (allByTrader_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByTrader_id);
    }

    public ApiResponse getByBranchId(Integer branch_id) {
        List<Trade> allByBranch_id = tradeRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByBranch_id);
    }

    public ApiResponse getByCustomerId(Integer customer_id) {
        List<Trade> allByCustomer_id = tradeRepository.findAllByCustomer_Id(customer_id);
        if (allByCustomer_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByCustomer_id);
    }

    public ApiResponse getByPayDate(Date payDate) {
        List<Trade> allByPayDate = tradeRepository.findAllByPayDate(payDate);
        if (allByPayDate.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByPayDate);
    }

    public ApiResponse getByPayStatus(Integer paymentStatus_id) {
        List<Trade> allByPaymentStatus_id = tradeRepository.findAllByPaymentStatus_Id(paymentStatus_id);
        if (allByPaymentStatus_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByPaymentStatus_id);
    }

    public ApiResponse getByPayMethod(Integer payMethod_id) {
        List<Trade> allByPaymentMethod_id = tradeRepository.findAllByPayMethod_Id(payMethod_id);
        if (allByPaymentMethod_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByPaymentMethod_id);
    }

    public ApiResponse getByAddress(Integer address_id) {
        List<Trade> allByAddress_id = tradeRepository.findAllByAddress_Id(address_id);
        if (allByAddress_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByAddress_id);
    }

    public ApiResponse deleteAll() {
        tradeRepository.deleteAll();
        return new ApiResponse("trades removed",false);
    }

    public ApiResponse deleteByTraderId(Integer trader_id) {
        tradeRepository.deleteByTrader_Id(trader_id);
        return new ApiResponse("deleted",true);
    }

    public ApiResponse deleteAllByTraderId(Integer trader_id) {
        tradeRepository.deleteAllByTrader_Id(trader_id);
        return new ApiResponse("deleted",true);
    }
}
