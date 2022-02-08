package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.repository.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
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
        for (Integer integer : productDto.getBranchId()) {
            Optional<Product> optionalProduct = productRepository.findByBarcodeAndBranch_IdAndActiveTrue(productDto.getBarcode(), integer);
            if (optionalProduct.isPresent()) {
                return new ApiResponse("BUNDAY SHTRIX KODLI MAXSULOT BOR", false);
            }
        }
        product = addProductDtotoProduct(product, productDto);
        productRepository.save(product);
        return new ApiResponse("SAVED", true, product);
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
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("NOT FOUND!", false);
        }
        Product product = optionalProduct.get();
        return new ApiResponse(true, product);
    }

    public ApiResponse deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            return new ApiResponse("NOT FOUND!", false);
        }
        return new ApiResponse("Deleted", true);
    }

    Product addProductDtotoProduct(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setBarcode(productDto.getBarcode());

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        optionalBrand.ifPresent(product::setBrand);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        product.setCategory(optionalCategory.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        product.setMeasurement(optionalMeasurement.get());

        product.setMinQuantity(productDto.getMinQuantity());

        List<Attachment> repositoryAllById = attachmentRepository.findAllById(productDto.getPhotoIds());
        product.setPhoto(repositoryAllById);

        product.setBuyPrice(productDto.getBuyPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setTax(productDto.getTax());

        List<Branch> branchRepositoryAllById = branchRepository.findAllById(productDto.getBranchId());
        product.setBranch(branchRepositoryAllById);

        if (product.getExpireDate() == null) {
            Date date = new Date(System.currentTimeMillis());
            product.setExpireDate(date);
        } else {
            product.setExpireDate(productDto.getExpireDate());
        }

        product.setDueDate(productDto.getDueDate());
        return product;
    }


    public ApiResponse getByBarcode(long barcode) {
        List<Product> allByBarcode = productRepository.findAllByBarcodeAndActiveTrue(barcode);
        if (allByBarcode.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByBarcode);
    }

    public ApiResponse getByCategory(Integer category_id) {
        List<Product> allByCategory_id = productRepository.findAllByCategory_IdAndActiveTrue(category_id);
        if (allByCategory_id.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByCategory_id);
    }

    public ApiResponse getByBrand(Integer brand_id) {
        List<Product> allByBrand_id = productRepository.findAllByBrand_IdAndActiveTrue(brand_id);
        if (allByBrand_id.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByBrand_id);
    }

    public ApiResponse getByBranch(Integer branch_id) {
        List<Product> allByBranch_id = productRepository.findAllByBranch_IdAndActiveTrue(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByBranch_id);
    }

    public ApiResponse getByBusiness(Integer businessId) {
        List<Product> allByBusinessId = productRepository.findAllByBusinessIdActiveTrue(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBusinessId);
    }

    public ApiResponse deleteProducts(List<Integer> ids) {
        productRepository.deleteAllById(ids);
        return new ApiResponse("DELETED",true);
    }
}
