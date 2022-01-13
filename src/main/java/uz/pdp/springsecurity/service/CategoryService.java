package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Category;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CategoryDto;
import uz.pdp.springsecurity.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getName()
        );

        categoryRepository.save(category);
        return new ApiResponse("Category added",true);
    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found",false);

        Category category = categoryRepository.getById(id);
        category.setName(categoryDto.getName());

        categoryRepository.save(category);
        return new ApiResponse("Category updated",true);
    }

    public ApiResponse get(Integer id) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found",false);

        return new ApiResponse("found",true,categoryRepository.findById(id).get());
    }

    public ApiResponse getAll() {

        return new ApiResponse("catch",true,categoryRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found",false);

        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted",true);
    }

    public ApiResponse deleteAll() {
        categoryRepository.deleteAll();
        return new ApiResponse("categories removed",true);
    }
}
