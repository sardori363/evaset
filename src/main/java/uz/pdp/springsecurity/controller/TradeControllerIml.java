package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.repository.TradeRepository;
import uz.pdp.springsecurity.service.TradeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@RestController
@RequestMapping("/api/trade")
public class TradeControllerIml {
    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    @CheckPermission("ADD_TRADE")
    @PostMapping
    public HttpEntity<?> create(@RequestBody TradeDTO tradeDTO) {
        ApiResponse apiResponse = tradeService.create(tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_TRADE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TradeDTO tradeDTO) {
        ApiResponse apiResponse = tradeService.edit(id, tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping
    public HttpEntity<?> get() {
        ApiResponse apiResponse = tradeService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_TRADE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeService.deleteTrade(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = tradeService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/delete-by-tradeId/trader_id")
    public HttpEntity<?> deleteByTradeId(@PathVariable Integer trader_id) {
        ApiResponse apiResponse = tradeService.deleteByTraderId(trader_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MY_TRADE")
    @DeleteMapping("/deleteAll-by-tradeId/trader_id")
    public HttpEntity<?> deleteAllByTradeId(@PathVariable Integer trader_id) {
        ApiResponse apiResponse = tradeService.deleteAllByTraderId(trader_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MY_TRADE")
    @GetMapping("/get-by-traderId/{trader_id}")
    public HttpEntity<?> getByTrader(@PathVariable Integer trader_id) {
        ApiResponse apiResponse = tradeService.getByTraderId(trader_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-branchId/{branch_id}")
    public HttpEntity<?> getByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = tradeService.getByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-customerId/{customer_id}")
    public HttpEntity<?> getByCustomer(@PathVariable Integer customer_id) {
        ApiResponse apiResponse = tradeService.getByCustomerId(customer_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-PayDate/{payDate}")
    public HttpEntity<?> getByPayDate(@PathVariable Date payDate) throws ParseException {
        ApiResponse apiResponse = tradeService.getByPayDate(payDate);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-PayStatusId/{paymentStatus_id}")
    public HttpEntity<?> getByPayStatus(@PathVariable Integer paymentStatus_id) {
        ApiResponse apiResponse = tradeService.getByPayStatus(paymentStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-PayMethodId/{payMethod_id}")
    public HttpEntity<?> getByPayMethod(@PathVariable Integer payMethod_id) {
        ApiResponse apiResponse = tradeService.getByPayMethod(payMethod_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-AddressId/{address_id}")
    public HttpEntity<?> getByAddress(@PathVariable Integer address_id) {
        ApiResponse apiResponse = tradeService.getByAddress(address_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DOWNLOAD_MEDIA")
    @GetMapping("/get-pdf/{id}")
    public HttpEntity<?> getPdf(@PathVariable Integer id , HttpServletResponse response) throws IOException {
        ApiResponse apiResponse = tradeService.createPdf(id , response);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-business/{}")
    public HttpEntity<?> getAllByBusinessId(@PathVariable Integer businessId) {
        ApiResponse apiResponse = tradeService.getAllByBusinessId(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}