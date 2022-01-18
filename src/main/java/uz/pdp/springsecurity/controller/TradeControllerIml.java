package uz.pdp.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.service.TradeService;

@RestController
@RequiredArgsConstructor
public class TradeControllerIml implements TradeController {
    @Autowired
    TradeService tradeService;



    @Override
    public ApiResponse<?> getAll() {
        ApiResponse objects = tradeService.getAll();
        return objects;
    }

    @Override
    public ApiResponse<TradeDTO> get(Integer id) {
        ApiResponse one = tradeService.getOne(id);
        return one;
    }

    @Override
    public ApiResponse<?> create(TradeDTO tradeDTO) {
        tradeService.create(tradeDTO);
        return null;
    }

    @Override
    public ApiResponse<?> edit(Integer id, TradeDTO tradeDTO) {
        return null;
    }

    @Override
    public ApiResponse<?> delete(Integer id) {
        return null;
    }
}
