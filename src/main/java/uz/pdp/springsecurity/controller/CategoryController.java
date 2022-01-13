package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CategoryDto;
import uz.pdp.springsecurity.repository.CategoryRepository;
import uz.pdp.springsecurity.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = categoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = categoryService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
