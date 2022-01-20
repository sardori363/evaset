package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeDTO;
import uz.pdp.springsecurity.repository.TradeRepository;
import uz.pdp.springsecurity.service.TradeService;

import java.util.Date;

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
        ApiResponse apiResponse = tradeService.edit(id, tradeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = tradeService.deleteTrade(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-traderId/{trader_id}")
    public HttpEntity<?> getByTrader(@PathVariable Integer trader_id) {
        ApiResponse apiResponse = tradeService.getByTraderId(trader_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-branchId/{branch_id}")
    public HttpEntity<?> getByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = tradeService.getByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-customerId/{customer_id}")
    public HttpEntity<?> getByCustomer(@PathVariable Integer customer_id) {
        ApiResponse apiResponse = tradeService.getByCustomerId(customer_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-PayDate/{payDate}")
    public HttpEntity<?> getByPayDate(@PathVariable Date payDate) {
        ApiResponse apiResponse = tradeService.getByPayDate(payDate);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-PayStatusId/{paymentStatus_id}")
    public HttpEntity<?> getByPayStatus(@PathVariable Integer paymentStatus_id) {
        ApiResponse apiResponse = tradeService.getByPayStatus(paymentStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-PayMethodId/{payMethod_id}")
    public HttpEntity<?> getByPayMethod(@PathVariable Integer payMethod_id) {
        ApiResponse apiResponse = tradeService.getByPayMethod(payMethod_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-AddressId/{address_id}")
    public HttpEntity<?> getByAddress(@PathVariable Integer address_id) {
        ApiResponse apiResponse = tradeService.getByAddress(address_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}