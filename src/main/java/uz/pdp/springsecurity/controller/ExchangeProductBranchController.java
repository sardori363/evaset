package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.service.ExchangeProductBranchService;

import java.sql.Date;


@RestController
@RequestMapping("/api/exchange-product-brach")
public class ExchangeProductBranchController {
    @Autowired
    ExchangeProductBranchService exchangeProductBrancService;


//    @CheckPermission("ADD_TRADE")
    @PostMapping
    public HttpEntity<?> create(@RequestBody ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBrancService.create(exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_TRADE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody  ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBrancService.edit(id, exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping
    public HttpEntity<?> get() {
        ApiResponse apiResponse = exchangeProductBrancService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_TRADE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBrancService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBrancService.deleteTrade(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_TRADE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = exchangeProductBrancService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-branch-id/{branch_id}")
    public HttpEntity<?> getByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = exchangeProductBrancService.shippedByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @CheckPermission("VIEW_ALL_TRADE")
    @GetMapping("/get-by-exchange-statusId/{paymentStatus_id}")
    public HttpEntity<?> getByPayStatus(@PathVariable Integer paymentStatus_id) {
        ApiResponse apiResponse = exchangeProductBrancService.getByStatusId(paymentStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}