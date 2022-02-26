package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProductDto;
import uz.pdp.springsecurity.service.ProductService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * YANGI PRODUCT QO'SHISH
     *
     * @param productDto
     * @return ApiResponse(success - > true message - > ADDED)
     */
    @CheckPermission("ADD_PRODUCT")
    @PostMapping()
    public HttpEntity<?> add(@Valid @RequestBody ProductDto productDto) throws ParseException {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * PRODUCTNI EDIT QILISH
     *
     * @param id
     * @param productDto
     * @return ApiResponse(success - > false message - > EDITED)
     */
    @CheckPermission("EDIT_PRODUCT")
    @PutMapping("{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI BITTA PRODUCTNI YANI MAXSULOTNI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.getProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI DELETE QILSISH
     *
     * @param id
     * @return ApiResponse(success - > true message - > DELETED)
     */
    @CheckPermission("DELETE_PRODUCT")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BIR NECHATA PRODUCTNI KELGAN IDLAR BO'YICHA O'CHIRISH
     *
     * @param ids
     * @return ApiResponse(success - > true message - > DELETED)
     */
    @CheckPermission("DELETE_PRODUCT")
    @DeleteMapping("/delete-few")
    public HttpEntity<?> deleteFew(@RequestBody List<Integer> ids) {
        ApiResponse apiResponse = productService.deleteProducts(ids);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * PRODUCTNI BARCODENI OLIB CHIQISH
     *
     * @param barcode
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/get-by-barcode/{barcode}")
    public HttpEntity<?> getByBarcode(@PathVariable long barcode) {
        ApiResponse apiResponse = productService.getByBarcode(barcode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * CATEGORY ID ORQALI MAHSULOTLARNI OLIB CHIQISH
     *
     * @param category_id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/get-by-category/{category_id}")
    public HttpEntity<?> getByCategory(@PathVariable Integer category_id) {
        ApiResponse apiResponse = productService.getByCategory(category_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * BRANDIDLAR ORQALI MAHSULOTLARNI OLIB CHIQISH
     *
     * @param brand_id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/get-by-brand/{brand_id}")
    public HttpEntity<?> getByBrand(@PathVariable Integer brand_id) {
        ApiResponse apiResponse = productService.getByBrand(brand_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BRANCHGA TEGISHLI BARCHA MAHSULOTLARNI OLIB CHIQISH
     *
     * @param branch_id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/get-by-branch/{branch_id}")
    public HttpEntity<?> getByBranch(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = productService.getByBranch(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * MEASUREMENT GA TEGISHLI BARCHA MAHSULOTLARNI OLIB CHIQISH
     *
     * @param measurement_id
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT")
    @GetMapping("/get-by-measurement/{measurement_id}")
    public HttpEntity<?> getByMeasurement(@PathVariable Integer measurement_id) {
        ApiResponse apiResponse = productService.getByMeasurement(measurement_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESSGA TEGISHLI BARCHA PRODUCTLARNI OLIB CHIQISH
     *
     * @param businessId
     * @return ApiResponse(success - > true object - > value)
     */
    @CheckPermission("VIEW_PRODUCT_ADMIN")
    @GetMapping("/get-by-business/{businessId}")
    public HttpEntity<?> getByBusiness(@PathVariable Integer businessId) {
        ApiResponse apiResponse = productService.getByBusiness(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
