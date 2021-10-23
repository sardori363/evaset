package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.entity.Category;
import uz.pdp.springsecurity.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    //    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody Category category) {
        categoryRepository.save(new Category(
                category.getName()
        ));
        return ResponseEntity.ok("Saved!");
    }

    //    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        List<Category> all = categoryRepository.findAll();

        return ResponseEntity.ok(all);
    }


//    @PreAuthorize(value = "hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
        return ResponseEntity.ok("OK");
    }
}
