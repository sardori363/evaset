package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PayStatusDto;
import uz.pdp.springsecurity.service.PayStatusService;

@RestController
@RequestMapping("/api/paystatus")
public class PayStatusController {
    @Autowired
    PayStatusService payStatusService;

    @CheckPermission("ADD_PAY_STATUS")
    @PostMapping
    public HttpEntity<?> add(@RequestBody PayStatusDto payStatusDto) {
        ApiResponse apiResponse = payStatusService.add(payStatusDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_PAY_STATUS")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody PayStatusDto payStatusDto) {
        ApiResponse apiResponse = payStatusService.edit(id, payStatusDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PAY_STATUS")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = payStatusService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_PAY_STATUS")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = payStatusService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PAY_STATUS_ADMIN")
    @GetMapping("/get-by-business/{business_id}")
    public HttpEntity<?> getAllByBusiness(@PathVariable Integer business_id) {
        ApiResponse apiResponse = payStatusService.getAllByBusiness(business_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
