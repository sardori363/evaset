package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.*;

import javax.validation.Valid;
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

    public ApiResponse addProduct(@Valid ProductDto productDto) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setBarcode(productDto.getBarcode());

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        optionalBrand.ifPresent(product::setBrandId);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        product.setCategoryId(optionalCategory.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        product.setMeasurementId(optionalMeasurement.get());

        product.setMinQuantity(productDto.getMinQuantity());

        List<Attachment> repositoryAllById = attachmentRepository.findAllById(productDto.getPhotoIds());
        product.setPhotoId(repositoryAllById);

        product.setBuyPrice(productDto.getBuyPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setTax(productDto.getTax());

        List<Branch> branchRepositoryAllById = branchRepository.findAllById(productDto.getPhotoIds());
        product.setBranchId(branchRepositoryAllById);




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
