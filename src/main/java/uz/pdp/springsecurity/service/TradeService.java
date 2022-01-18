package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.payload.ProductTradeDto;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.repository.*;

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
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;


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
        Optional<Customer> optionalCustomer = customerRepository.findById(tradeDTO.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            return new ApiResponse("CUSTOMER NOT FOUND", false);
        }
        trade.setCustomer(optionalCustomer.get());

        Optional<User> optionalUser = userRepository.findById(tradeDTO.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("TRADER NOT FOUND", false);
        }
        trade.setTrader(optionalUser.get());

        for (ProductTradeDto productTradeDto : tradeDTO.getProductTraderDto()) {
            Optional<Product> optionalProduct = productRepository.findByName(productTradeDto.getName());
            Integer quantity = optionalProduct.get().getQuantity();
            if(quantity<productTradeDto.getQuantity())return new ApiResponse("BAZADA YETARLI MAXSULOT YO'Q" , false);
            Product product = optionalProduct.get();
            product.setQuantity(product.getQuantity() - productTradeDto.getQuantity());
            productRepository.save(product);

        }


        Optional<Branch> optionalBranch = branchRepository.findById(tradeDTO.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }
        trade.setBranch(optionalBranch.get());


        Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(tradeDTO.getPaymentStatusId());
        if (optionalPaymentStatus.isEmpty()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }
        trade.setPaymentStatus(optionalPaymentStatus.get());


        return null;
    }
}
