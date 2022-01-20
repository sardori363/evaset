package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.entity.TradeHistory;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
import uz.pdp.springsecurity.repository.TradeHistoryRepository;
import uz.pdp.springsecurity.repository.TradeRepository;

import java.util.Optional;

@Service
public class TradeHistoryService {

    @Autowired
    TradeHistoryRepository tradeHistoryRepository;

    @Autowired
    TradeRepository tradeRepository;

    public ApiResponse add(TradeHistoryDto tradeHistoryDto) {
        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setPaidAmount(tradeHistoryDto.getPaidAmount());
        tradeHistory.setPaidDate(tradeHistoryDto.getPaidDate());

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        if (!optionalTrade.isPresent()) return new ApiResponse("not found",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        tradeHistory.setPaymentMethod(tradeHistoryDto.getPaymentMethod());

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
        tradeHistory.setPaymentMethod(tradeHistoryDto.getPaymentMethod());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("updated",true);
    }

    public ApiResponse getOne(Integer id) {
        return null;
    }

    public ApiResponse getAll() {
        return null;
    }

    public ApiResponse deleteOne(Integer id) {
        return null;
    }

    public ApiResponse deleteAll() {
        return null;
    }

    public ApiResponse getByTradeId(Integer trade_id) {
        return null;
    }
}
