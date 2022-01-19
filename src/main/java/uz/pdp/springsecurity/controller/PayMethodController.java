package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PayMethodDto;
import uz.pdp.springsecurity.service.PayMethodService;

@RestController
@RequestMapping("/api/paymethod")
public class PayMethodController {
    @Autowired
    PayMethodService payMethodService;

//    @CheckPermission("ADD_PAY_METHOD")
    @PostMapping
    public HttpEntity<?> add(@RequestBody PayMethodDto payMethodDto) {
        ApiResponse apiResponse = payMethodService.add(payMethodDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_PAY_METHOD")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody PayMethodDto payMethodDto) {
        ApiResponse apiResponse = payMethodService.edit(id, payMethodDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PAY_METHOD")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = payMethodService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PAY_METHOD")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = payMethodService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_PAY_METHOD")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = payMethodService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_PAY_METHOD")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = payMethodService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
