package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.repository.TradeRepository;
import uz.pdp.springsecurity.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeControllerIml {
    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    @GetMapping
    public HttpEntity<?> get() {
        ApiResponse apiResponse = tradeService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> create(@RequestBody TradeDTO tradeDTO) {
        ApiResponse apiResponse = tradeService.create(tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TradeDTO tradeDTO) {
        ApiResponse apiResponse = tradeService.edit(id,tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeService.deleteTrade(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-trade-with-traderId/{trader_id}")
    public HttpEntity<?> getByTraderId(@PathVariable Integer trader_id) {
        ApiResponse apiResponse = tradeService.getByTraderId(trader_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
