package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.AddressRepository;
import uz.pdp.springsecurity.service.AddressService;
import uz.pdp.springsecurity.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class ProductController {
    @Autowired
    ProductService productService;


    @PostMapping()
    public HttpEntity<?> addAddress(@Valid @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editAddress(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAddress(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.getProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping()
    public HttpEntity<?> getAddresses() {
        ApiResponse apiResponse = productService.getProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAddress(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping()
    public HttpEntity<?> deleteAddresses() {
        ApiResponse apiResponse = productService.deleteProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
