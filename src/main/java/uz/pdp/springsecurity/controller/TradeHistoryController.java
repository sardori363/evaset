package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.RoleDto;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
import uz.pdp.springsecurity.service.TradeHistoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/trade-history")
public class TradeHistoryController {
    @Autowired
    TradeHistoryService tradeHistoryService;

    @CheckPermission("ADD_TRADE")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.add(tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_TRADE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.edit(id, tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_TRADE")
    @GetMapping("/get-one/{id}")

    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = tradeHistoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/delete-trade/{trade_id}")
    public HttpEntity<?> deleteByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.deleteByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/{trade_id}")
    public HttpEntity<?> deleteAllByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.deleteAllByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MY_TRADE")
    @GetMapping("/get-all-by-tradeId/{trade_id}")
    public HttpEntity<?> getAllByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getAllByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MY_TRADE")

    @GetMapping("/{trade_id}")
    public HttpEntity<?> getByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MY_TRADE")
    @GetMapping("/{id}/{trade_id}")
    public HttpEntity<?> getByTradeIdAndId(@PathVariable Integer id,@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getByTradeIdAndId(id,trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = tradeHistoryService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
