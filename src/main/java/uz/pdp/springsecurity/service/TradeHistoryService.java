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
        if (!optionalTrade.isPresent()) return new ApiResponse("not found",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeHistoryDto.getPaymentMethodId());
        tradeHistory.setPaymentMethod(optionalPaymentMethod.get().getType());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("saved",true);
    }

    public ApiResponse edit(Integer id, TradeHistoryDto tradeHistoryDto) {
        Optional<TradeHistory> optionalHistory = tradeHistoryRepository.findById(id);
        if (!optionalHistory.isPresent()) return new ApiResponse("not found",false);

        TradeHistory tradeHistory = optionalHistory.get();

        tradeHistory.setPaidAmount(tradeHistoryDto.getPaidAmount());
        tradeHistory.setPaidDate(tradeHistoryDto.getPaidDate());

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        if (!optionalTrade.isPresent()) return new ApiResponse("not found",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        Optional<PaymentMethod> optionalPaymentMethod = payMethodRepository.findById(tradeHistoryDto.getPaymentMethodId());
        tradeHistory.setPaymentMethod(optionalPaymentMethod.get().getType());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("updated",true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<TradeHistory> optionalTradeHistory = tradeHistoryRepository.findById(id);
        if (optionalTradeHistory.isEmpty()) {
            return new ApiResponse("NOT FOUND" , false);
        }
        return new ApiResponse("FOUND" , true , optionalTradeHistory.get());
    }

    public ApiResponse getAll() {
        List<TradeHistory> tradeHistoryRepositoryAll = tradeHistoryRepository.findAll();
        return new ApiResponse(true , tradeHistoryRepositoryAll);
    }

    public ApiResponse deleteOne(Integer id) {
        Optional<TradeHistory> optional = tradeHistoryRepository.findById(id);
        if (!optional.isPresent()) {
        return new ApiResponse("NOT FOUND",false);
        }
            return new ApiResponse("FOUND" , true , optional.get());
    }

    public ApiResponse deleteAllByTradeId(Integer trade_id) {
        tradeHistoryRepository.deleteAllByTrade_Id(trade_id);
        return new ApiResponse("DELETED" , true);
    }

    public ApiResponse getAllByTradeId(Integer trade_id) {
        List<TradeHistory> allByTrade_id = tradeHistoryRepository.findAllByTrade_Id(trade_id);
        return new ApiResponse("FOUND", true, allByTrade_id);
    }

    public ApiResponse getByTradeIdAndId(Integer id, Integer trade_id) {
        Optional<TradeHistory> byIdAndTrade_id = tradeHistoryRepository.findByIdAndTrade_Id(id, trade_id);
        if (!byIdAndTrade_id.isPresent()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,byIdAndTrade_id);
    }

    public ApiResponse deleteAll() {
        tradeHistoryRepository.deleteAll();
        return new ApiResponse("trade history removed",true);
    }

    public ApiResponse deleteByTradeId(Integer trade_id) {
        tradeHistoryRepository.deleteByTrade_Id(trade_id);
        return new ApiResponse("deleted",true);
    }

    public ApiResponse getByTradeId(Integer trade_id) {
        if (tradeHistoryRepository.existsById(trade_id)) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,tradeHistoryRepository.findByTrade_Id(trade_id).get());
    }

}
