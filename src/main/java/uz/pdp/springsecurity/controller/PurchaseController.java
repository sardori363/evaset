package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PurchaseDto;
import uz.pdp.springsecurity.service.PurchaseService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    /**
     * YANGI XARID QO'SHISH
     *
     * @param purchaseDto
     * @return ApiResponse(success - > true message - > ADDED)
     */
    @CheckPermission("ADD_PURCHASE")
    @PostMapping
    public HttpEntity<?> add(@RequestBody PurchaseDto purchaseDto) {
        ApiResponse apiResponse = purchaseService.add(purchaseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI XARIDNI TAHRIRLASH
     *
     * @param id
     * @param purchaseDto
     * @return ApiResponse(success - > true message - > EDITED)
     */
    @CheckPermission("EDIT_PURCHASE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody PurchaseDto purchaseDto) {
        ApiResponse apiResponse = purchaseService.edit(id, purchaseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI XARIDNI OLIB CHIQISH
     *
     * @param id
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = purchaseService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * ID ORQALI XARIDNI OLIB DELETE QILISH
     *
     * @param id
     * @return ApiResponse(success - > true message - > DELETED)
     */
    @CheckPermission("DELETE_PURCHASE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = purchaseService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * YETKAZIB BERUVCHI (SUPPLIER) ID'SI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param dealer_id
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-dealerId/{dealer_id}")
    public HttpEntity<?> getByDealerId(@PathVariable Integer dealer_id) {
        ApiResponse apiResponse = purchaseService.getByDealerId(dealer_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * XARID STATUSI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param purchaseStatus_id
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-purchaseStatus/{purchaseStatus_id}")
    public HttpEntity<?> getByPurchaseStatusId(@PathVariable Integer purchaseStatus_id) {
        ApiResponse apiResponse = purchaseService.getByPurchaseStatusId(purchaseStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * TOLOV STATUSI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param paymentStatus_id
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-paymentStatus/{paymentStatus_id}")
    public HttpEntity<?> getByPaymentStatusId(@PathVariable Integer paymentStatus_id) {
        ApiResponse apiResponse = purchaseService.getByPaymentStatusId(paymentStatus_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * FILIAL ID'SI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param branch_id
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-branch/{branch_id}")
    public HttpEntity<?> getByBranchId(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = purchaseService.getByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * XARID SANASI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param date
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-date/{date}")
    public HttpEntity<?> getByDate(@PathVariable Date date) {
        ApiResponse apiResponse = purchaseService.getByDate(date);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * JAMI SUMMA ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param totalSum
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("get-purchase-by-totalSum/{totalSum}")
    public HttpEntity<?> getByTotalSum(@PathVariable double totalSum) {
        ApiResponse apiResponse = purchaseService.getByTotalSum(totalSum);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * XARID ID'SI ORQALI XARIDNING PDF CHEKINI DOWNLOAD QILISH
     *
     * @param id
     * @return ApiResponse(success - > true message - > CREATED)
     */
    @CheckPermission("VIEW_PURCHASE")
    @GetMapping("/get-pdf/{id}")
    public HttpEntity<?> getPdf(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        ApiResponse apiResponse = purchaseService.getPdfFile(id, response);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BUSINESS ID'SI ORQALI BARCHA XARIDLARNI OLIB CHIQISH
     *
     * @param businessId
     * @return ApiResponse(success - > true message - > FOUND)
     */
    @CheckPermission("VIEW_PURCHASE_ADMIN")
    @GetMapping("/get-by-business/{businessId}")
    public HttpEntity<?> getAllByBusiness(@PathVariable Integer businessId) {
        ApiResponse apiResponse = purchaseService.getAllByBusiness(businessId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
