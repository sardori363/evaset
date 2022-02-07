package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BrandDto;
import uz.pdp.springsecurity.repository.BrandRepository;
import uz.pdp.springsecurity.service.BrandService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandService brandService;

    @CheckPermission("ADD_BRAND")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = brandService.addBrand(brandDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_BRAND")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = brandService.editBrand(id, brandDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_BRAND")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = brandService.getBrand(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_BRAND")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = brandService.deleteBrand(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_BRAND")
    @GetMapping("by-businessId/{business_id}")
    public HttpEntity<?> getAllByBranchId(@PathVariable Integer business_id) {
        ApiResponse apiResponse = brandService.getAllByBusiness(business_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
