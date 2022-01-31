package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BusinessDto;
import uz.pdp.springsecurity.repository.BusinessRepository;

import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    BusinessRepository businessRepository;


    public ApiResponse add(BusinessDto businessDto) {
        if (businessRepository.existsByName(businessDto.getName()))
            return new ApiResponse("a business with that name already exists", false);
        Business business = new Business();
        business.setName(businessDto.getName());
        business.setDescription(businessDto.getDescription());

        businessRepository.save(business);
        return new ApiResponse("saved",true);
    }

    public ApiResponse edit(Integer id, BusinessDto businessDto) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        if (optionalBusiness.isEmpty()) return new ApiResponse("business not found",false);

        if (businessRepository.existsByName(businessDto.getName()))
            return new ApiResponse("a business with that name already exists", false);

        Business business = optionalBusiness.get();
        business.setName(businessDto.getName());
        business.setDescription(businessDto.getDescription());

        businessRepository.save(business);
        return new ApiResponse("edited",true);
    }

    public ApiResponse getOne(Integer id) {
        if (!businessRepository.existsById(id)) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,businessRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,businessRepository.findAll());
    }

    public ApiResponse deleteOne(Integer id) {
        if (!businessRepository.existsById(id)) return new ApiResponse("not found",false);
        businessRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }

    public ApiResponse deleteAll() {
        businessRepository.deleteAll();
        return new ApiResponse("business removed",true);
    }
}
