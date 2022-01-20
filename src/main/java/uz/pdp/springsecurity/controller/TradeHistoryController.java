package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.RoleDto;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
import uz.pdp.springsecurity.service.TradeHistoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sell-history")
public class TradeHistoryController {
    @Autowired
    TradeHistoryService tradeHistoryService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.add(tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.edit(id, tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = tradeHistoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{trade_id}")
    public HttpEntity<?> deleteAllByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.deleteAllByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{trade_id}")
    public HttpEntity<?> getByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id,trade_id}")
    public HttpEntity<?> getByTradeIdAndId(@PathVariable Integer id,@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getByTradeIdAndId(id,trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = tradeHistoryService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
