package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.repository.AddressRepository;
import uz.pdp.springsecurity.service.AddressService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @CheckPermission("ADD_ADDRESS")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_ADDRESS")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ADDRESS")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.getAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ADDRESS")
    @GetMapping
    public HttpEntity<?> get() {
        ApiResponse apiResponse = addressService.getAddresses();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_ADDRESS")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_ADDRESS")
    @DeleteMapping
    public HttpEntity<?> delete() {
        ApiResponse apiResponse = addressService.deleteAddresses();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
