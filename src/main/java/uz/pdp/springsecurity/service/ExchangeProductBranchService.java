package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.ExchangeProduct;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.ExchangeProductBranchRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeProductBranchService {

    @Autowired
    ExchangeProductBranchRepository exchangeProductBranchRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse create(ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ExchangeProductBranch exchangeProductBranch = new ExchangeProductBranch();

        Integer shippedBranchId = exchangeProductBranchDTO.getShippedBranchId();
        Optional<Branch> optionalBranch = branchRepository.findById(shippedBranchId);
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND SHIPPED BRANCH", false);
        }
        exchangeProductBranch.setShippedBranch(optionalBranch.get());

        Integer receivedBranch = exchangeProductBranchDTO.getReceivedBranchId();
        Optional<Branch> branchOptional = branchRepository.findById(receivedBranch);
        if (branchOptional.isEmpty()) {
            return new ApiResponse("NOT FOUND RECEIVED BRANCH", false);
        }
        exchangeProductBranch.setReceivedBranch(branchOptional.get());

        Date date = new Date(System.currentTimeMillis());
        exchangeProductBranch.setExchangeDate(date);

        exchangeProductBranch.setDescription(exchangeProductBranchDTO.getDescription());

        List<ExchangeProduct> exchangeProduct = exchangeProductBranch.getExchangeProduct();

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
