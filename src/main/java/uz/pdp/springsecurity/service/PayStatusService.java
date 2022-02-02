package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.PaymentStatus;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.PayStatusDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.PaymentStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PayStatusService {

    @Autowired
    PaymentStatusRepository payStatusRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(PayStatusDto payStatusDto) {
        boolean b = payStatusRepository.existsByStatus(payStatusDto.getStatus());
        if (b) return new ApiResponse("such a payment status already exists", false);

        Optional<Branch> optionalBranch = branchRepository.findById(payStatusDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }

        PaymentStatus paymentStatus = new PaymentStatus(
                payStatusDto.getStatus(),
                optionalBranch.get()
        );
        payStatusRepository.save(paymentStatus);
        return new ApiResponse("saved", true);
    }

    public ApiResponse edit(Integer id, PayStatusDto payStatusDto) {
        Optional<PaymentStatus> statusOptional = payStatusRepository.findById(id);
        if (!statusOptional.isPresent()) return new ApiResponse("not found", false);

        boolean b = payStatusRepository.existsByStatus(payStatusDto.getStatus());
        if (b) return new ApiResponse("such a payment status already exists", false);

        PaymentStatus paymentStatus = payStatusRepository.getById(id);
        paymentStatus.setStatus(payStatusDto.getStatus());

        payStatusRepository.save(paymentStatus);
        return new ApiResponse("edited", true);
    }

    public ApiResponse get(Integer id) {
        if (!payStatusRepository.existsById(id)) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, payStatusRepository.findById(id).get());
    }

    public ApiResponse delete(Integer id) {
        if (!payStatusRepository.existsById(id)) return new ApiResponse("not found", false);
        payStatusRepository.deleteById(id);
        return new ApiResponse("deleted", true);
    }

    public ApiResponse getAllByBranch(Integer branch_id) {
        List<PaymentStatus> allByBranch_id = payStatusRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBranch_id);
    }

    public ApiResponse getAllByBusiness(Integer business_id) {
        List<PaymentStatus> allByBranch_business_id = payStatusRepository.findAllByBranch_Business_Id(business_id);
        if (allByBranch_business_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBranch_business_id);
    }
}
