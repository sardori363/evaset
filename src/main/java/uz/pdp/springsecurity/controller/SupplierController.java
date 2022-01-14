package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.SupplierDto;
import uz.pdp.springsecurity.repository.SupplierRepository;
import uz.pdp.springsecurity.service.SupplierService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody SupplierDto supplierDto) {
        ApiResponse apiResponse = supplierService.add(supplierDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody SupplierDto supplierDto) {
        ApiResponse apiResponse = supplierService.edit(id,supplierDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = supplierService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = supplierService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
