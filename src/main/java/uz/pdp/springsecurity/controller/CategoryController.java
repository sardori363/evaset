package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CategoryDto;
import uz.pdp.springsecurity.repository.CategoryRepository;
import uz.pdp.springsecurity.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    /**
     * YANGI MAHSULOTLAR CATEGORYSINI QO'SHISH
     *
     * @param categoryDto
     * @return ApiResponse(success - > true message - > ADDED)
     */
    @CheckPermission("ADD_CATEGORY")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * CATEGORYNI EDIT QILSIH
     *
     * @param id
     * @param categoryDto
     * @return ApiResponse(success - > true message - > EDITED)
     */
    @CheckPermission("EDIT_CATEGORY")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA CATEGORYNI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_CATEGORY")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI CATEGORYNI O'CHIRISH
     *
     * @param id
     * @return ApiResponse(success - > true message - > DELETED)
     */
    @CheckPermission("DELETE_CATEGORY")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESS ID ORQALI CATEGORYI OLIB CHIQISH
     *
     * @param businessId
     * @return ApiResponse(success - > true object - > value)
     */

    @CheckPermission("VIEW_CATEGORY_ADMIN")
    @GetMapping("/get-by-businessId/{businessId}")
    public HttpEntity<?> getAllByBusinessId(@PathVariable Integer businessId) {
        ApiResponse apiResponse = categoryService.getAllByBusinessId(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
