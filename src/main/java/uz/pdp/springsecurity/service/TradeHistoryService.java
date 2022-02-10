package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.PaymentMethod;
import uz.pdp.springsecurity.entity.TradeHistory;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
import uz.pdp.springsecurity.repository.PayMethodRepository;
import uz.pdp.springsecurity.repository.TradeHistoryRepository;
import uz.pdp.springsecurity.repository.TradeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TradeHistoryService {
    @Autowired
    TradeHistoryRepository tradeHistoryRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    PayMethodRepository payMethodRepository;

    public ApiResponse add(TradeHistoryDto tradeHistoryDto) {

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        TradeHistory tradeHistory = new TradeHistory();
        if (optionalTrade.isEmpty()) return new ApiResponse("TRADE NOT FOUND",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeHistoryDto.getPaymentMethodId());
        tradeHistory.setPaymentMethod(optionalPaymentMethod.get().getType());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("ADDED",true);
    }

    public ApiResponse edit(Integer id, TradeHistoryDto tradeHistoryDto) {
        Optional<TradeHistory> optionalHistory = tradeHistoryRepository.findById(id);
        if (optionalHistory.isEmpty()) return new ApiResponse("NOT FOUND",false);

        TradeHistory tradeHistory = optionalHistory.get();

        tradeHistory.setPaidAmount(tradeHistoryDto.getPaidAmount());
        tradeHistory.setPaidDate(tradeHistoryDto.getPaidDate());

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        if (optionalTrade.isEmpty()) return new ApiResponse("NOT FOUND",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeHistoryDto.getPaymentMethodId());
        tradeHistory.setPaymentMethod(optionalPaymentMethod.get().getType());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("EDITED",true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<TradeHistory> optionalTradeHistory = tradeHistoryRepository.findById(id);
        return optionalTradeHistory.map(tradeHistory -> new ApiResponse("FOUND", true, tradeHistory)).orElseGet(() -> new ApiResponse("NOT FOUND", false));
    }

    public ApiResponse deleteOne(Integer id) {
        Optional<TradeHistory> optional = tradeHistoryRepository.findById(id);
        return optional.map(tradeHistory -> new ApiResponse("FOUND", true, tradeHistory)).orElseGet(() -> new ApiResponse("NOT FOUND", false));
    }

    public ApiResponse deleteAllByTradeId(Integer trade_id) {
        tradeHistoryRepository.deleteAllByTrade_Id(trade_id);
        return new ApiResponse("DELETED" , true);
    }

    public ApiResponse getAllByTradeId(Integer trade_id) {
        List<TradeHistory> allByTrade_id = tradeHistoryRepository.findAllByTrade_Id(trade_id);
        return new ApiResponse("FOUND", true, allByTrade_id);
    }

    public ApiResponse deleteByTradeId(Integer trade_id) {
        tradeHistoryRepository.deleteByTrade_Id(trade_id);
        return new ApiResponse("DELETED",true);
    }

    public ApiResponse getByTradeId(Integer trade_id) {
        if (tradeHistoryRepository.existsById(trade_id)) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,tradeHistoryRepository.findByTrade_Id(trade_id).get());
    }

    public ApiResponse getAllByBranch(Integer branch_id) {
        List<TradeHistory> allByTrade_branch_id = tradeHistoryRepository.findAllByTrade_Branch_Id(branch_id);
        if (allByTrade_branch_id.isEmpty()) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,allByTrade_branch_id);
    }

    public ApiResponse getAllByBusiness(Integer business_id) {
        List<TradeHistory> allByTrade_branch_business_id = tradeHistoryRepository.findAllByTrade_Branch_Business_Id(business_id);
        if (allByTrade_branch_business_id.isEmpty()) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,allByTrade_branch_business_id);
    }
}
