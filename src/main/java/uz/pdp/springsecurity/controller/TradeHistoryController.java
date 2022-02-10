package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeHistoryDto;
import uz.pdp.springsecurity.service.TradeHistoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/trade-history")
public class TradeHistoryController {


    @Autowired
    TradeHistoryService tradeHistoryService;

    /**
     * YANGI SAVDO TARIXI QOSHISH
     *
     * @param tradeHistoryDto
     * @return ApiResponse(success - > true, message - > ADDED)
     */
    @CheckPermission("ADD_TRADE")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.add(tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI SAVDO TARIXINI TAHRIRLASH
     *
     * @param tradeHistoryDto
     * @return ApiResponse(success - > true, message - > ADDED)
     */
    @CheckPermission("EDIT_TRADE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody TradeHistoryDto tradeHistoryDto) {
        ApiResponse apiResponse = tradeHistoryService.edit(id, tradeHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA SAVDO TARIXINI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true, object - > value)
     */
    @CheckPermission("VIEW_TRADE")
    @GetMapping("/get-one/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA SAVDO TARIXINI DELETE QILISH
     *
     * @param id
     * @return ApiResponse(success - > true, message - > DELETED)
     */
    @CheckPermission("DELETE_TRADE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeHistoryService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * SAVDO ID'SI ORQALI BITTA SAVDO TARIXINI DELETE QILISH
     *
     * @param trade_id
     * @return ApiResponse(success - > true, message - > DELETED)
     */
    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/delete-trade/{trade_id}")
    public HttpEntity<?> deleteByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.deleteByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * SAVDO ID'SI ORQALI BARCHA SAVDO TARIXLARINI DELETE QILISH
     *
     * @param trade_id
     * @return ApiResponse(success - > true, message - > DELETED)
     */
    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/{trade_id}")
    public HttpEntity<?> deleteAllByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.deleteAllByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * SAVDO ID'SI ORQALI BARCHA SAVDO TARIXLARINI OLIB CHIQISH
     *
     * @param trade_id
     * @return ApiResponse(success - > true, object - > value)
     */
    @CheckPermission("VIEW_MY_TRADE")
    @GetMapping("/get-all-by-tradeId/{trade_id}")
    public HttpEntity<?> getAllByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getAllByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * SAVDO ID'SI ORQALI BITTA SAVDO TARIXINI OLIB CHIQISH
     *
     * @param trade_id
     * @return ApiResponse(success - > true, object - > value)
     */
    @CheckPermission("VIEW_MY_TRADE")
    @GetMapping("/{trade_id}")
    public HttpEntity<?> getByTradeId(@PathVariable Integer trade_id) {
        ApiResponse apiResponse = tradeHistoryService.getByTradeId(trade_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * FILIAL ID'SI ORQALI BARCHA SAVDO TARIXLARINI OLIB CHIQISH
     *
     * @param branch_id
     * @return ApiResponse(success - > true, object - > value)
     */
    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-branch/{branch_id}")
    public HttpEntity<?> getAllByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = tradeHistoryService.getAllByBranch(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESS ID'SI ORQALI BARCHA SAVDO TARIXLARINI OLIB CHIQISH
     *
     * @param business_id
     * @return ApiResponse(success - > true, object - > value)
     */
    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-business/{business_id}")
    public HttpEntity<?> getAllByBusiness(@PathVariable Integer business_id) {
        ApiResponse apiResponse = tradeHistoryService.getAllByBusiness(business_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
