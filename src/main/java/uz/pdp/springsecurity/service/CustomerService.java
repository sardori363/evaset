package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Customer;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CustomerDto;
import uz.pdp.springsecurity.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public ApiResponse add(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getName(),
                customerDto.getPhoneNumber(),
                customerDto.getTelegram()
        );
        customerRepository.save(customer);
        return new ApiResponse("Customer saved",true);
    }

    public ApiResponse edit(Integer id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) return new ApiResponse("Customer not found",false);

        Customer customer = customerRepository.getById(id);
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setTelegram(customerDto.getTelegram());

        customerRepository.save(customer);
        return new ApiResponse("Customer updated",true);
    }

    public ApiResponse get(Integer id) {
        if (!customerRepository.existsById(id)) return new ApiResponse("Customer not found",false);
        return new ApiResponse("found",true,customerRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,customerRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!customerRepository.existsById(id)) return new ApiResponse("Customer not found",false);
        customerRepository.deleteById(id);
        return new ApiResponse("Customer deleted",true);
    }

    public ApiResponse deleteAll() {
        customerRepository.deleteAll();
        return new ApiResponse("Customers removed",true);
    }
}
