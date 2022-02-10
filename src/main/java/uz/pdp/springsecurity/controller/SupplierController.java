package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
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

    /**
     * TA'MINOTCHI QOSHISH
     *
     * @param supplierDto
     * @return ApiResponse(success - > true, message - > ADDED)
     */
    @CheckPermission("ADD_SUPPLIER")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody SupplierDto supplierDto) {
        ApiResponse apiResponse = supplierService.add(supplierDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI TA'MINOTCHINI TAHRIRLASH
     *
     * @param id
     * @param supplierDto
     * @return ApiResponse(success - > true, message - > EDITED)
     */
    @CheckPermission("EDIT_SUPPLIER")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody SupplierDto supplierDto) {
        ApiResponse apiResponse = supplierService.edit(id,supplierDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA TA'MINOTCHINI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true, message - > FOUND)
     */
    @CheckPermission("VIEW_SUPPLIER")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA TA'MINOTCHINI DELETE QILISH
     *
     * @param id
     * @return ApiResponse(success - > true, message - > DELETED)
     */
    @CheckPermission("DELETE_SUPPLIER")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESS ID'SI ORQALI BARCHA TA'MINOTCHILARNI OLIB CHIQISH
     *
     * @param businessId
     * @return ApiResponse(success - > true, message - > FOUND)
     */
    @CheckPermission("VIEW_SUPPLIER_ADMIN")
    @GetMapping("/get-by-business/{businessId}")
    public HttpEntity<?> getAllByBusiness(@PathVariable Integer businessId) {
        ApiResponse apiResponse = supplierService.getAllByBusiness(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
