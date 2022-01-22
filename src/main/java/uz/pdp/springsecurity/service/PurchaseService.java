package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PurchaseDto;
import uz.pdp.springsecurity.payload.PurchaseProductDto;
import uz.pdp.springsecurity.repository.*;

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
    PurchaseProductRepository productRepository;

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

        purchase.setDate(purchaseDto.getDate());
        purchase.setDescription(purchaseDto.getDescription());
        purchase.setDeliveryPrice(purchaseDto.getDeliveryPrice());


        return null;
    }
}
