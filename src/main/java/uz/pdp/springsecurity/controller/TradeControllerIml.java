package uz.pdp.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeProductDTO;
import uz.pdp.springsecurity.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeControllerIml {
    @Autowired
    TradeService tradeService;



    public ApiResponse<?> getAll() {
        ApiResponse objects = tradeService.getAll();
        return objects;
    }


    public ApiResponse<TradeProductDTO> get(Integer id) {
        ApiResponse one = tradeService.getOne(id);
        return one;
    }


    @PostMapping
    public HttpEntity<?> create(@RequestBody TradeProductDTO tradeDTO) {
        ApiResponse apiResponse = tradeService.create(tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    public ApiResponse<?> edit(Integer id, TradeProductDTO tradeDTO) {
        return null;
    }


    public ApiResponse<?> delete(Integer id) {
        return null;
    }
}
