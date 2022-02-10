package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BusinessDto;
import uz.pdp.springsecurity.service.BusinessService;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    /**
     * YANGI BUSINESS QO'SHISH
     *
     * @param businessDto
     * @return ApiResponse(success - > true message - > ADDED)
     */
    @CheckPermission("ADD_BUSINESS")
    @PostMapping
    public HttpEntity<?> add(@RequestBody BusinessDto businessDto) {
        ApiResponse apiResponse = businessService.add(businessDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESSNI IDSI ORQALI EDIT QILISH
     *
     * @param id
     * @param businessDto
     * @return ApiResponse(success - > true message - > EDITED)
     */
    @CheckPermission("EDIT_BUSINESS")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody BusinessDto businessDto) {
        ApiResponse apiResponse = businessService.edit(id, businessDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BITTA ID ORQALI BUSINESSNI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_BUSINESS")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = businessService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * HAMMA BUSINESSLARNI  OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_BUSINESS")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = businessService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI O'CHIRISH
     *
     * @param id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("DELETE_BUSINESS")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = businessService.deleteOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
