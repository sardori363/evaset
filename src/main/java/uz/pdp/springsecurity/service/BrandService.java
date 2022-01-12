package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Brand;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BrandDto;
import uz.pdp.springsecurity.repository.BrandRepository;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public ApiResponse addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());

        brandRepository.save(brand);
        return new ApiResponse("Brand successfully added",true);
    }

    public ApiResponse editBrand(Integer id, BrandDto brandDto) {
        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found",false);

        Brand brand = brandRepository.getById(id);
        brand.setName(brandDto.getName());

        brandRepository.save(brand);
        return new ApiResponse("Brand successfully updated",true);
    }

    public ApiResponse getBrand(Integer id) {
        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found",false);
        return new ApiResponse("Found",true,brandRepository.findById(id).get());
    }

    public ApiResponse getBrands() {
        return new ApiResponse("Found",true,brandRepository.findAll());
    }

    public ApiResponse deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found",false);
        brandRepository.deleteById(id);
        return new ApiResponse("Brand deleted",true);
    }

    public ApiResponse deleteBrands() {
        brandRepository.deleteAll();
        return new ApiResponse("Brands successfully deleted",true);
    }
}
