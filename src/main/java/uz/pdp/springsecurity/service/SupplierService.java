package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Supplier;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.SupplierDto;
import uz.pdp.springsecurity.repository.SupplierRepository;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public ApiResponse add(SupplierDto supplierDto) {
        Supplier supplier = new Supplier(
                supplierDto.getName(),
                supplierDto.getPhoneNumber(),
                supplierDto.getTelegram(),
                supplierDto.getSupplierType()
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
}
