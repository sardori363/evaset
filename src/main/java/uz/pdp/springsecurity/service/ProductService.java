package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.ProductRepository;

import javax.validation.Valid;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public ApiResponse addProduct(@Valid ProductDto addressDto) {
        return null;
    }

    public ApiResponse editProduct(Integer id, ProductDto productDto) {
        return null;
    }

    public ApiResponse getProduct(Integer id) {
        return null;
    }

    public ApiResponse getProducts() {
        return null;
    }

    public ApiResponse deleteProduct(Integer id) {
        return null;
    }

    public ApiResponse deleteProducts() {
        return null;
    }
}
