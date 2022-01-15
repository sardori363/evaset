package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public ApiResponse addProduct(@Valid ProductDto productDto) throws ParseException {
        Product product = new Product();
        product = addProductDtotoProduct(product, productDto);
        productRepository.save(product);
        return new ApiResponse(true, product);
    }

    public ApiResponse editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        product = addProductDtotoProduct(product, productDto);
        productRepository.save(product);
        return new ApiResponse(true, product);
    }

    public ApiResponse getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("NOT FOUND!", false);
        }
        Product product = optionalProduct.get();
        return new ApiResponse(true, product);
    }

    public ApiResponse getProducts() {
        List<Product> allProduct = productRepository.findAll();
        return new ApiResponse(allProduct);
    }

    public ApiResponse deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        }else {
            return new ApiResponse("NOT FOUND!",false);
        }
        return new ApiResponse(true);
    }

    public ApiResponse deleteProducts() {
        productRepository.deleteAll();
        return new ApiResponse(true);
    }

    Product addProductDtotoProduct(Product product, ProductDto productDto) {
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

        if (product.getExpireDate() == null) {
            Date date = new Date(System.currentTimeMillis());
            product.setExpireDate(date);
        } else {
            product.setExpireDate(productDto.getExpireDate());
        }

        product.setDueDate(productDto.getDueDate());
        return product;
    }


}
