package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.PaymentMethod;
import uz.pdp.springsecurity.entity.PaymentStatus;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.payload.ApiResponse;
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



    public ApiResponse getAll() {
        List<Trade> all = tradeRepository.findAll();
        return new ApiResponse(true, all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Trade> optionalTrade = tradeRepository.findById(id);
        if (optionalTrade.isPresent()) {
        return new ApiResponse(true , optionalTrade.get());
        }
    return new ApiResponse("NOT FOUND",false);
    }

    public ApiResponse create(TradeDTO tradeDTO) {
        Trade trade = new Trade();
     return null;
    }
}
