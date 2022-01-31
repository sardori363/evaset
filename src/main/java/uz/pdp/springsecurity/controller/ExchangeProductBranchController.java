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

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = exchangeProductBranchService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_EXCHANGE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBranchService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_EXCHANGE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = exchangeProductBranchService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-byDate/{exchangeDate}")
    public HttpEntity<?> getByDate(Date exchangeDate) {
        ApiResponse apiResponse = exchangeProductBranchService.getByDate(exchangeDate);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-statusId/{exchangeStatus_id}")
    public HttpEntity<?> getByExchangeStatus(Integer exchangeStatus_id) {
        ApiResponse apiResponse = exchangeProductBranchService.getByStatusId(exchangeStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}