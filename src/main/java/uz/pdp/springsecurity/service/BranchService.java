package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Address;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BranchDto;
import uz.pdp.springsecurity.repository.AddressRepository;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.BusinessRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse addBranch(BranchDto branchDto) {
        Branch branch = new Branch();

        branch.setName(branchDto.getName());

        Optional<Address> byId = addressRepository.findById(branchDto.getAddressId());
        if (byId.isEmpty()) return new ApiResponse("Address not found", false);
        branch.setAddress(byId.get());

        Optional<Business> optionalBusiness = businessRepository.findById(branchDto.getBusinessId());
        if (optionalBusiness.isEmpty()) return new ApiResponse("business not found", false);
        branch.setBusiness(optionalBusiness.get());

        branchRepository.save(branch);
        return new ApiResponse("Branch successfully saved", true);
    }


    public ApiResponse editBranch(Integer id, BranchDto branchDto) {
        if (!branchRepository.existsById(id)) return new ApiResponse("Branch not found", false);

        Branch branch = branchRepository.getById(id);
        branch.setName(branchDto.getName());

        if (!addressRepository.existsById(branchDto.getAddressId())) return new ApiResponse("Address not found", false);
        branch.setAddress(branch.getAddress());

        Optional<Business> optionalBusiness = businessRepository.findById(branchDto.getBusinessId());
        if (optionalBusiness.isEmpty()) return new ApiResponse("business not found", false);
        branch.setBusiness(optionalBusiness.get());

        branchRepository.save(branch);
        return new ApiResponse("Branch successfully edited", true);
    }

    public ApiResponse getBranch(Integer id) {
        if (!branchRepository.existsById(id)) return new ApiResponse("Branch not found", false);
        return new ApiResponse("Branch found", true, branchRepository.findById(id).get());
    }

    public ApiResponse getBranches() {
        return new ApiResponse("Catch", true, branchRepository.findAll());
    }

    public ApiResponse deleteBranch(Integer id) {
        if (!branchRepository.existsById(id)) return new ApiResponse("Branch not found", false);

        branchRepository.deleteById(id);
        return new ApiResponse("Branch successfully deleted", true);
    }

    public ApiResponse deleteBranches() {
        branchRepository.deleteAll();
        return new ApiResponse("Branches successfully deleted", true);
    }

    public ApiResponse getByBusinessId(Integer business_id) {
        List<Branch> allByBusiness_id = branchRepository.findAllByBusiness_Id(business_id);
        if (allByBusiness_id.isEmpty()) return new ApiResponse("business not found",false);
        return new ApiResponse("found",true,allByBusiness_id);
    }
}
