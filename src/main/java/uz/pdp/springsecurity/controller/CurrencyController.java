package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CurrencyDto;
import uz.pdp.springsecurity.repository.CurrencyRepository;
import uz.pdp.springsecurity.service.CurrencyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;

    @CheckPermission("ADD_CURRENCY")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CurrencyDto currencyDto) {
        ApiResponse apiResponse = currencyService.add(currencyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_CURRENCY")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto) {
        ApiResponse apiResponse = currencyService.edit(id, currencyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_CURRENCY")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = currencyService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_CURRENCY")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = currencyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_CURRENCY")
    @GetMapping("get-by-branchId/{branch_id}")
    public HttpEntity<?> getAllByBranchId(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = currencyService.getAllByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
