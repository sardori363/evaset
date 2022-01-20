package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.TradeHistory;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
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

    public ApiResponse add(TradeHistoryDto tradeHistoryDto) {

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        TradeHistory tradeHistory = new TradeHistory();
        if (optionalTrade.isEmpty()) return new ApiResponse("not found",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        tradeHistory.setPaymentMethod(tradeHistoryDto.getPaymentMethod());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("saved",true);
    }

    public ApiResponse edit(Integer id, TradeHistoryDto tradeHistoryDto) {
        Optional<TradeHistory> optionalHistory = tradeHistoryRepository.findById(id);
        if (optionalHistory.isEmpty()) return new ApiResponse("not found",false);

        TradeHistory tradeHistory = optionalHistory.get();

        tradeHistory.setPaidAmount(tradeHistoryDto.getPaidAmount());
        tradeHistory.setPaidDate(tradeHistoryDto.getPaidDate());

        Optional<Trade> optionalTrade = tradeRepository.findById(tradeHistoryDto.getTradeId());
        if (optionalTrade.isEmpty()) return new ApiResponse("not found",false);
        tradeHistory.setTrade(optionalTrade.get());
        tradeHistory.setDescription(tradeHistoryDto.getDescription());
        tradeHistory.setPaymentMethod(tradeHistoryDto.getPaymentMethod());

        tradeHistoryRepository.save(tradeHistory);
        return new ApiResponse("updated",true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<TradeHistory> allByTrade_id = tradeHistoryRepository.findById(id);
        return allByTrade_id.map(tradeHistory -> new ApiResponse("FOUND", true, allByTrade_id.get())).orElseGet(() -> new ApiResponse("NOT FOUND", false));
    }

    public ApiResponse getAll() {
        List<TradeHistory> tradeHistoryRepositoryAll = tradeHistoryRepository.findAll();
        return new ApiResponse(true , tradeHistoryRepositoryAll);
    }

    public ApiResponse deleteOne(Integer id) {
        Optional<TradeHistory> optional = tradeHistoryRepository.findById(id);
        if (optional.isEmpty()) {
        return new ApiResponse("NOT FOUND",false);
        }
            return new ApiResponse("FOUND" , true , optional.get());
    }

    public ApiResponse deleteAll(Integer trade_id) {
        tradeHistoryRepository.deleteAllByTrade_Id(trade_id);
        return new ApiResponse("DELETED" , true);
    }

    public ApiResponse getByTradeId(Integer trade_id) {
        List<TradeHistory> allByTrade_id = tradeHistoryRepository.findAllByTrade_Id(trade_id);
        return new ApiResponse("FOUND", true, allByTrade_id);
    }
}
