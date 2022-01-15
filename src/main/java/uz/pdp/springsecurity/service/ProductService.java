package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.ProductRepository;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public ApiResponse add(ProductDto productDto) {
        return null;
    }
}
