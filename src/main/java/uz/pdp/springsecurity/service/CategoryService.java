package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.entity.Category;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CategoryDto;
import uz.pdp.springsecurity.repository.BusinessRepository;
import uz.pdp.springsecurity.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse add(CategoryDto categoryDto) {
        Optional<Business> optionalBusiness = businessRepository.findById(categoryDto.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("NOT FOUND BUSINESS", false);
        }

        Category category = new Category(
                categoryDto.getName(),
                optionalBusiness.get()
        );

        categoryRepository.save(category);
        return new ApiResponse("Category added", true);
    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found", false);

        Category category = categoryRepository.getById(id);
        category.setName(categoryDto.getName());

        categoryRepository.save(category);
        return new ApiResponse("Category updated", true);
    }

    public ApiResponse get(Integer id) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found", false);

        return new ApiResponse("found", true, categoryRepository.findById(id).get());
    }

    public ApiResponse delete(Integer id) {
        if (!categoryRepository.existsById(id)) return new ApiResponse("Category not found", false);

        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted", true);
    }


    public ApiResponse getAllByBusinessId(Integer businessId) {
        List<Category> allByBusinessId = categoryRepository.findAllByBusiness_Id(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBusinessId);
    }
}
