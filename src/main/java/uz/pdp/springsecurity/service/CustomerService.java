package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.entity.Customer;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CustomerDto;
import uz.pdp.springsecurity.repository.BusinessRepository;
import uz.pdp.springsecurity.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse add(CustomerDto customerDto) {
        Optional<Business> optionalBusiness = businessRepository.findById(customerDto.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }
        Customer customer = new Customer(
                customerDto.getName(),
                customerDto.getPhoneNumber(),
                customerDto.getTelegram(),
                optionalBusiness.get()
        );
        customerRepository.save(customer);
        return new ApiResponse("ADDED", true);
    }

    public ApiResponse edit(Integer id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);
        Optional<Business> optionalBusiness = businessRepository.findById(customerDto.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("BRANCH NOT FOUND", false);
        }

        Customer customer = customerRepository.getById(id);
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setTelegram(customerDto.getTelegram());
        customer.setBusiness(optionalBusiness.get());

        customerRepository.save(customer);
        return new ApiResponse("EDITED", true);
    }

    public ApiResponse get(Integer id) {
        if (!customerRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);
        return new ApiResponse("FOUND", true, customerRepository.findById(id).get());
    }

    public ApiResponse delete(Integer id) {
        if (!customerRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);
        customerRepository.deleteById(id);
        return new ApiResponse("DELETED", true);
    }

    public ApiResponse getAllByBusinessId(Integer businessId) {
        List<Customer> allByBusinessId = customerRepository.findAllByBusiness_Id(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,allByBusinessId);
    }
}
