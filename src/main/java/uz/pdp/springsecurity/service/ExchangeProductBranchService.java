package uz.pdp.springsecurity.service;

import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.payload.TradeDTO;

@Service
public class ExchangeProductBranchService {
    public ApiResponse create(ExchangeProductBranchDTO exchangeProductBranchDTO) {
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
