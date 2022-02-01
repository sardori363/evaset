package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Address;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.repository.AddressRepository;
import uz.pdp.springsecurity.repository.BusinessRepository;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());
        address.setHome(addressDto.getHome());

        addressRepository.save(address);
        return new ApiResponse("Address successfully added", true);
    }

    public ApiResponse editAddress(Integer id, AddressDto addressDto) {
        if (!addressRepository.existsById(id)) return new ApiResponse("Address not found", false);
        Address address = addressRepository.getById(id);
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());
        address.setHome(addressDto.getHome());

        addressRepository.save(address);
        return new ApiResponse("Address successfully updated", true);
    }

    public ApiResponse getAddress(Integer id) {
        if (!addressRepository.existsById(id)) return new ApiResponse("Address not found", false);
        return new ApiResponse("Address found", true, addressRepository.findById(id).get());
    }

    public ApiResponse getAddresses() {
        return new ApiResponse("Catch", true, addressRepository.findAll());
    }

    public ApiResponse deleteAddress(Integer id) {
        if (!addressRepository.existsById(id)) return new ApiResponse("Address not found", false);

        addressRepository.deleteById(id);
        return new ApiResponse("Address successfully deleted", true);
    }

    public ApiResponse deleteAddresses() {
        addressRepository.deleteAll();
        return new ApiResponse("Addresses successfully deleted", true);
    }
}
