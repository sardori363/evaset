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
    ExchangeProductBranchService exchangeProductBrancService;


    @CheckPermission("ADD_EXCHANGE")
    @PostMapping
    public HttpEntity<?> create(@RequestBody ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBrancService.create(exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_EXCHANGE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ApiResponse apiResponse = exchangeProductBrancService.edit(id, exchangeProductBranchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBrancService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = exchangeProductBrancService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_EXCHANGE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = exchangeProductBrancService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_EXCHANGE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = exchangeProductBrancService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-byDate/{exchangeDate}")
    public HttpEntity<?> getByDate(Date exchangeDate) {
        ApiResponse apiResponse = exchangeProductBrancService.getByDate(exchangeDate);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_EXCHANGE")
    @GetMapping("/get-by-statusId/{exchangeStatus_id}")
    public HttpEntity<?> getByExchangeStatus(Integer exchangeStatus_id) {
        ApiResponse apiResponse = exchangeProductBrancService.getByStatusId(exchangeStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}