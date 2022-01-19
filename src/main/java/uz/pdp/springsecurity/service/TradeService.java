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
        if (!apiResponse.isSuccess()) {
            return new ApiResponse("ERROR", false);
        }
        return new ApiResponse("SAVED", true);
    }

    public ApiResponse deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
        return new ApiResponse("DELATED" ,true);
    }

    public ApiResponse edit(Integer id, TradeDTO tradeDTO) {
        Optional<TradeProduct> optionalTradeProduct = tradeProductRepository.findById(id);
        if (optionalTradeProduct.isEmpty()) {
            return new ApiResponse("NOT FOUND TRADE", false);
        }
        Trade trade = new Trade();
        ApiResponse apiResponse = addTraade(trade, tradeDTO);

        if (!apiResponse.isSuccess()) {
            return new ApiResponse("ERROR", false);
        }
        return new ApiResponse("SAVED", true);
    }




    public  ApiResponse addTraade(Trade trade , TradeDTO tradeDTO){
        Optional<Customer> optionalCustomer = customerRepository.findById(tradeDTO.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            return new ApiResponse("CUSTOMER NOT FOUND", false);
        }
        trade.setCustomer(optionalCustomer.get());

        /**
         * SOTUVCHI SAQLANDI
         */
        Optional<User> optionalUser = userRepository.findById(tradeDTO.getUserId());
        if (optionalUser.isEmpty()) {
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
        if (tradeDTO.getAmountPaid() != null || tradeDTO.getAmountPaid() != 0) {
            trade.setAmountPaid(tradeDTO.getAmountPaid());
            trade.setLoan(sum - tradeDTO.getAmountPaid());
        } else {
            trade.setAmountPaid(0.0);
            trade.setLoan(sum - tradeDTO.getAmountPaid());
        }


        /**
         * BRANCH SAQLANDI
         */
        Optional<Branch> optionalBranch = branchRepository.findById(tradeDTO.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }
        trade.setBranch(optionalBranch.get());


        /**
         * PAYMAENTSTATUS SAQLANDI
         */
        Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(tradeDTO.getPaymentStatusId());
        if (optionalPaymentStatus.isEmpty()) {
            return new ApiResponse("PAYMAENTSTATUS NOT FOUND", false);
        }
        trade.setPaymentStatus(optionalPaymentStatus.get());

        /**
         * PAYMAENTMETHOD SAQLANDI
         */
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeDTO.getPayMethodId());
        if (optionalPaymentMethod.isEmpty()) {
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
        return new ApiResponse(true , trade);
    }
}
