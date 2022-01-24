package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PurchaseDto;
import uz.pdp.springsecurity.payload.PurchaseProductDto;
import uz.pdp.springsecurity.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseProductRepository purchaseProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ExchangeStatusRepository exchangeStatusRepository;

    @Autowired
    PaymentStatusRepository paymentStatusRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        ApiResponse apiResponse = addPurchase(purchase, purchaseDto);

        return apiResponse;
    }

    public ApiResponse edit(Integer id, PurchaseDto purchaseDto) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (!optionalPurchase.isPresent()) return new ApiResponse("purchase not found",false);

        Purchase purchase = optionalPurchase.get();
        ApiResponse apiResponse = addPurchase(purchase,purchaseDto);

        if (!apiResponse.isSuccess()) return new ApiResponse("error",false);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse get(Integer id) {
        if (!purchaseRepository.existsById(id)) return new ApiResponse("purchase not found",false);
        return new ApiResponse("found",true,purchaseRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,purchaseRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!purchaseRepository.existsById(id)) return new ApiResponse("purchase not found",false);
        purchaseRepository.deleteById(id);
        return new ApiResponse("purchase deleted",false);
    }

    public ApiResponse deleteAll() {
        purchaseRepository.deleteAll();
        return new ApiResponse("purchases removed",true);
    }

    private ApiResponse addPurchase(Purchase purchase, PurchaseDto purchaseDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(purchaseDto.getDealerId());
        if (!optionalSupplier.isPresent()) return new ApiResponse("supplier not found",false);
        purchase.setDealer(optionalSupplier.get());

        Optional<ExchangeStatus> optionalPurchaseStatus = exchangeStatusRepository.findById(purchaseDto.getPurchaseStatusId());
        if (!optionalPurchaseStatus.isPresent()) return new ApiResponse("purchase status not found",false);
        purchase.setPurchaseStatus(optionalPurchaseStatus.get());

        Optional<PaymentStatus> optionalPaymentStatus = paymentStatusRepository.findById(purchaseDto.getPaymentStatusId());
        if (!optionalPaymentStatus.isPresent()) return new ApiResponse("payment status not found",false);
        purchase.setPaymentStatus(optionalPaymentStatus.get());

        Optional<Branch> optionalBranch = branchRepository.findById(purchaseDto.getBranchId());
        if (!optionalBranch.isPresent()) return new ApiResponse("branch not found",false);
        purchase.setBranch(optionalBranch.get());

        List<PurchaseProductDto> purchaseProductsDto = purchaseDto.getPurchaseProductsDto();
        List<PurchaseProduct> purchaseProductList = new ArrayList<>();
        double sum = 0;
        for (PurchaseProductDto purchaseProductDto : purchaseProductsDto) {
            PurchaseProduct purchaseProduct = new PurchaseProduct();

            Integer productPurchaseId = purchaseProductDto.getProductPurchaseId();
            Optional<Product> optionalProduct = productRepository.findById(productPurchaseId);
            Product product = optionalProduct.get();

            Integer purchasedQuantity = purchaseProductDto.getPurchasedQuantity();

            double buyPrice = optionalProduct.get().getBuyPrice();

            sum = sum+buyPrice*purchasedQuantity;

            product.setQuantity(product.getQuantity()+purchasedQuantity);
            productRepository.save(product);

            purchaseProduct.setProduct(optionalProduct.get());
            purchaseProduct.setPurchasedQuantity(purchasedQuantity);

            purchaseProductRepository.save(purchaseProduct);
            purchaseProductList.add(purchaseProduct);
        }

        purchase.setTotalSum(sum+purchaseDto.getDeliveryPrice());

        purchase.setPurchaseProductList(purchaseProductList);

        purchase.setDate(purchaseDto.getDate());

        purchase.setDescription(purchaseDto.getDescription());

        purchase.setDeliveryPrice(purchaseDto.getDeliveryPrice());

        purchaseRepository.save(purchase);

        return new ApiResponse("SAVED" , true);
    }

    public ApiResponse getByDealerId(Integer dealer_id) {
        List<Purchase> allByDealer_id = purchaseRepository.findAllByDealer_Id(dealer_id);
        if (allByDealer_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByDealer_id);
    }

    public ApiResponse getByPurchaseStatusId(Integer purchaseStatus_id) {
        List<Purchase> allByPurchaseStatus_id = purchaseRepository.findAllByPurchaseStatus_Id(purchaseStatus_id);
        if (allByPurchaseStatus_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByPurchaseStatus_id);
    }

    public ApiResponse getByPaymentStatusId(Integer paymentStatus_id) {
        List<Purchase> allByPaymentStatus_id = purchaseRepository.findAllByPaymentStatus_Id(paymentStatus_id);
        if (allByPaymentStatus_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByPaymentStatus_id);
    }

    public ApiResponse getByBranchId(Integer branch_id) {
        List<Purchase> allByBranch_id = purchaseRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBranch_id);
    }

    public ApiResponse getByDate(Date date) {
        List<Purchase> allByDate = purchaseRepository.findAllByDate(date);
        if (allByDate.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByDate);
    }

    public ApiResponse getByTotalSum(double totalSum) {
        List<Purchase> allByTotalSum = purchaseRepository.findAllByTotalSum(totalSum);
        if (allByTotalSum.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByTotalSum);
    }
}
