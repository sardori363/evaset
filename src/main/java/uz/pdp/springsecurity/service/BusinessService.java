package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BusinessDto;
import uz.pdp.springsecurity.repository.BusinessRepository;
import uz.pdp.springsecurity.repository.RoleRepository;
import uz.pdp.springsecurity.repository.UserRepository;

import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    public ApiResponse add(BusinessDto businessDto) {
        if (businessRepository.existsByName(businessDto.getName()))
            return new ApiResponse("A BUSINESS WITH THAT NAME ALREADY EXISTS", false);
        Business business = new Business();
        business.setName(businessDto.getName());
        business.setDescription(businessDto.getDescription());

        businessRepository.save(business);
        return new ApiResponse("ADDED",true);
    }

    public ApiResponse edit(Integer id, BusinessDto businessDto) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        if (optionalBusiness.isEmpty()) return new ApiResponse("BUSINESS NOT FOUND",false);

        if (businessRepository.existsByName(businessDto.getName()))
            return new ApiResponse("A BUSINESS WITH THAT NAME ALREADY EXISTS", false);

        Business business = optionalBusiness.get();
        business.setName(businessDto.getName());
        business.setDescription(businessDto.getDescription());

        businessRepository.save(business);
        return new ApiResponse("EDITED",true);
    }

    public ApiResponse getOne(Integer id) {
        if (!businessRepository.existsById(id)) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,businessRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("FOUND",true,businessRepository.findAll());
    }

    public ApiResponse deleteOne(Integer id) {

        for (User user : userRepository.findAllByBusiness_Id(id)) {
            userRepository.deleteById(user.getId());
        }
        for (Role role : roleRepository.findAllByBusiness_Id(id)) {
            roleRepository.deleteById(role.getId());
        }

        if (!businessRepository.existsById(id)) return new ApiResponse("NOT FOUND",false);
        businessRepository.deleteById(id);
        return new ApiResponse("DELETED",true);
    }
}
