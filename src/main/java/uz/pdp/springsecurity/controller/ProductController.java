package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.AddressRepository;
import uz.pdp.springsecurity.service.AddressService;
import uz.pdp.springsecurity.service.ProductService;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

//    @CheckPermission("ADD_PRODUCT")
    @PostMapping()
    public HttpEntity<?> add(@Valid @RequestBody ProductDto productDto) throws ParseException {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_PRODUCT")
    @PutMapping("{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.getProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_PRODUCT")
    @GetMapping()
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = productService.getProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_PRODUCT")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_PRODUCT")
    @DeleteMapping()
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = productService.deleteProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-barcode/{barcode}")
    public HttpEntity<?> getByBarcode(@PathVariable long barcode) {
        ApiResponse apiResponse = productService.getByBarcode(barcode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-category/{category_id}")
    public HttpEntity<?> getByCategory(@PathVariable Integer category_id) {
        ApiResponse apiResponse = productService.getByCategory(category_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-brand/{brand_id}")
    public HttpEntity<?> getByBrand(@PathVariable Integer brand_id) {
        ApiResponse apiResponse = productService.getByBrand(brand_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-by-branch/{branch_id}")
    public HttpEntity<?> getByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = productService.getByBranch(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
