package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.ExchangeProduct;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;
import uz.pdp.springsecurity.entity.Product;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.payload.ExchangeProductDTO;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.ExchangeProductBranchRepository;
import uz.pdp.springsecurity.repository.ProductRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeProductBranchService {

    @Autowired
    ExchangeProductBranchRepository exchangeProductBranchRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse create(ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ExchangeProductBranch exchangeProductBranch = new ExchangeProductBranch();

        Integer shippedBranchId = exchangeProductBranchDTO.getShippedBranchId();
        Optional<Branch> optionalBranch = branchRepository.findById(shippedBranchId);
        if (!optionalBranch.isPresent()) {
            return new ApiResponse("NOT FOUND SHIPPED BRANCH", false);
        }
        exchangeProductBranch.setShippedBranch(optionalBranch.get());

        Integer receivedBranch = exchangeProductBranchDTO.getReceivedBranchId();
        Optional<Branch> branchOptional = branchRepository.findById(receivedBranch);
        if (!branchOptional.isPresent()) {
            return new ApiResponse("NOT FOUND RECEIVED BRANCH", false);
        }
        exchangeProductBranch.setReceivedBranch(branchOptional.get());

        if (exchangeProductBranchDTO.getExchangeDate() == null) {
            Date date = new Date(System.currentTimeMillis());
            exchangeProductBranch.setExchangeDate(date);
        } else {
            exchangeProductBranch.setExchangeDate(exchangeProductBranchDTO.getExchangeDate());
        }

        exchangeProductBranch.setDescription(exchangeProductBranchDTO.getDescription());

        List<ExchangeProductDTO> exchangeProductDTOS = exchangeProductBranchDTO.getExchangeProductDTOS();
        for (ExchangeProductDTO productDTO : exchangeProductDTOS) {
            Optional<Product> exchangeProduct = productRepository.findByIdAndBranch_Id(productDTO.getProductExchangeId(), optionalBranch.get().getId());

            Product product = exchangeProduct.get();
            product.setQuantity(product.getQuantity() - productDTO.getExchangeProductQuantity());
            productRepository.save(product);

            Optional<Product> optionalProduct = productRepository.findByNameAndBranch_Id(product.getName(), receivedBranch);
            Product receiveProduct = optionalProduct.get();


        }

        return null;
    }


    public ApiResponse getAll() {
        return null;
    }

    public ApiResponse getOne(Integer id) {
        return null;
    }

    public ApiResponse edit(Integer id, ExchangeProductBranchDTO exchangeProductBranchDTO) {
        return null;
    }

    public ApiResponse deleteTrade(Integer id) {
        return null;
    }

    public ApiResponse deleteAll() {
        return null;
    }

    public ApiResponse getByBranchId(Integer branch_id) {
        return null;
    }

    public ApiResponse shippedByBranchId(Integer branch_id) {
        return null;
    }

    public ApiResponse getByStatusId(Integer paymentStatus_id) {
        return null;
    }
}
