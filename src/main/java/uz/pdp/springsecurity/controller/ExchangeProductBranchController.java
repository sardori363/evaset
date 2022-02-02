package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.service.ExchangeProductBranchService;

import java.sql.Date;


@RestController
@RequestMapping("/api/exchange-product-brach")
public class ExchangeProductBranchController {
    @Autowired
    ExchangeProductBranchService exchangeProductBranchService;


    @CheckPermission("ADD_EXCHANGE")
    @PostMapping
    public HttpEntity<?> create(@RequestBody ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBranchService.create(exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_EXCHANGE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBranchService.edit(id, exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBranchService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_EXCHANGE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBranchService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-byDate/{exchangeDate}")
    public HttpEntity<?> getByDate(Date exchangeDate) {
        ApiResponse apiResponse = exchangeProductBranchService.getByDate(exchangeDate);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-statusId/{exchangeStatus_id}/{branch_id}")
    public HttpEntity<?> getByExchangeStatus(@PathVariable Integer exchangeStatus_id, @PathVariable Integer branch_id) {
        ApiResponse apiResponse = exchangeProductBranchService.getByStatusId(exchangeStatus_id, branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-businessId/{businessId}")
    public HttpEntity<?> getByBusinessId(@PathVariable Integer businessId) {
        ApiResponse apiResponse = exchangeProductBranchService.getByBusinessId(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-shipped-branch/{shippedBranch_id}")
    public HttpEntity<?> getByShippedBranchId(@PathVariable Integer shippedBranch_id) {
        ApiResponse apiResponse = exchangeProductBranchService.getByShippedBranchId(shippedBranch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-received-branch/{receivedBranch_id}")
    public HttpEntity<?> getByReceivedBranchId(@PathVariable Integer receivedBranch_id) {
        ApiResponse apiResponse = exchangeProductBranchService.getByReceivedBranchId(receivedBranch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}