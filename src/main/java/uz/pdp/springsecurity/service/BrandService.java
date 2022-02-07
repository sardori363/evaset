package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Brand;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BrandDto;
import uz.pdp.springsecurity.repository.BrandRepository;
import uz.pdp.springsecurity.repository.BusinessRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        Optional<Business> optionalBusiness = businessRepository.findById(brandDto.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("NOT FOUND BUSINESS", false);
        }
        brand.setBusiness(optionalBusiness.get());
        brandRepository.save(brand);
        return new ApiResponse("Brand successfully added", true);
    }

    public ApiResponse editBrand(Integer id, BrandDto brandDto) {

        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found", false);

        Brand brand = brandRepository.getById(id);
        brand.setName(brandDto.getName());

        Optional<Business> optionalBusiness = businessRepository.findById(brandDto.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("NOT FOUND BUSINESS", false);
        }

        brand.setBusiness(optionalBusiness.get());

        brandRepository.save(brand);
        return new ApiResponse("Brand successfully updated", true);
    }

    public ApiResponse getBrand(Integer id) {
        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found", false);
        return new ApiResponse("Found", true, brandRepository.findById(id).get());
    }

    public ApiResponse deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) return new ApiResponse("Brand not found", false);
        brandRepository.deleteById(id);
        return new ApiResponse("Brand deleted", true);
    }

    public ApiResponse getAllByBusiness(Integer business_id) {
        List<Brand> allByBranch_id = brandRepository.findAllByBusiness_Id(business_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByBranch_id);
    }
}
