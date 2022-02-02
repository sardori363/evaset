package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Supplier;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.SupplierDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(SupplierDto supplierDto) {
        Optional<Branch> optionalBranch = branchRepository.findById(supplierDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH",false);
        }
        Supplier supplier = new Supplier(
                supplierDto.getName(),
                supplierDto.getPhoneNumber(),
                supplierDto.getTelegram(),
                supplierDto.getSupplierType(),
                optionalBranch.get()
        );
            supplierRepository.save(supplier);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, SupplierDto supplierDto) {
        if (!supplierRepository.existsById(id)) return new ApiResponse("supplier not found",false);

        Supplier supplier = supplierRepository.getById(id);
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setTelegram(supplierDto.getTelegram());
        supplier.setSupplierType(supplierDto.getSupplierType());

        supplierRepository.save(supplier);
        return new ApiResponse("edited", true);
    }

    public ApiResponse get(Integer id) {
        if (!supplierRepository.existsById(id)) return new ApiResponse("supplier not found",false);
        return new ApiResponse("found",true,supplierRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,supplierRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!supplierRepository.existsById(id)) return new ApiResponse("supplier not found",false);
        supplierRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }

    public ApiResponse deleteAll() {
        supplierRepository.deleteAll();
        return new ApiResponse("suppliers removed",true);
    }

    public ApiResponse getAllByBranch(Integer branch_id) {
        List<Supplier> allByBranch_id = supplierRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBranch_id);
    }

    public ApiResponse getAllByBusiness(Integer businessId) {
        List<Supplier> allByBusinessId = supplierRepository.findAllByBusinessId(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBusinessId);
    }
}
