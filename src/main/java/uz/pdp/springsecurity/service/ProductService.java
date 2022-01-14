package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setBarcode(productDto.getBarcode());

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("brand not found",false);
        product.setBrandId(optionalBrand.get());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("category not found",false);
        product.setCategoryId(optionalCategory.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) return new ApiResponse("measurement not found",false);
        product.setMeasurementId(optionalMeasurement.get());

        product.setMinQuantity(productDto.getMinQuantity());

        List<Attachment> optionalPhoto = attachmentRepository.findAllById(productDto.getPhotoId());
        if (optionalPhoto.isEmpty()) return new ApiResponse("photos not found",false);
        product.setPhotoId(optionalPhoto);

        product.setBuyPrice(productDto.getBuyPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setTax(productDto.getTax());

        List<Branch> optionalBranches = branchRepository.findAllById(productDto.getBranchId());
        if (optionalBranches.isEmpty()) return new ApiResponse("photos not found",false);
        product.setBranchId(optionalBranches);

        product.setQuantity(productDto.getQuantity());

        productRepository.save(product);
        return new ApiResponse("product successfully saved",true);
    }
}
