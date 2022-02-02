package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Outlay;
import uz.pdp.springsecurity.entity.OutlayCategory;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;
import uz.pdp.springsecurity.repository.OutlayRepository;
import uz.pdp.springsecurity.repository.UserRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutlayService {
    @Autowired
    OutlayRepository outlayRepository;

    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse add(OutlayDto outlayDto) {
        Outlay outlay = new Outlay();

        Optional<OutlayCategory> optionalCategory = outlayCategoryRepository.findById(outlayDto.getOutlayCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Outlay category not found", false);
        outlay.setOutlayCategory(optionalCategory.get());

        outlay.setTotalSum(outlayDto.getTotalSum());

        Optional<Branch> optionalBranch = branchRepository.findById(outlayDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }
        outlay.setBranch(optionalBranch.get());

        Optional<User> spender = userRepository.findById(outlayDto.getSpenderId());
        if (!spender.isPresent()) return new ApiResponse("spender not found", false);
        outlay.setSpender(spender.get());

        outlay.setDescription(outlayDto.getDescription());
        outlay.setDate(outlayDto.getDate());

        outlayRepository.save(outlay);
        return new ApiResponse("Outlay saved", true);
    }

    public ApiResponse edit(Integer id, OutlayDto outlayDto) {
        if (!outlayRepository.existsById(id)) return new ApiResponse("Outlay not found", false);

        Outlay outlay = outlayRepository.getById(id);

        Optional<OutlayCategory> optionalCategory = outlayCategoryRepository.findById(outlayDto.getOutlayCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Outlay category not found", false);
        outlay.setOutlayCategory(optionalCategory.get());

        outlay.setTotalSum(outlayDto.getTotalSum());

        Optional<Branch> optionalBranch = branchRepository.findById(outlayDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }
        outlay.setBranch(optionalBranch.get());

        Optional<User> spender = userRepository.findById(outlayDto.getSpenderId());
        if (!spender.isPresent()) return new ApiResponse("spender not found", false);
        outlay.setSpender(spender.get());

        outlay.setDescription(outlayDto.getDescription());
        outlay.setDate(outlayDto.getDate());

        outlayRepository.save(outlay);
        return new ApiResponse("Outlay updated", true);
    }

    public ApiResponse get(Integer id) {
        if (!outlayRepository.existsById(id)) return new ApiResponse("Outlay not found", false);
        return new ApiResponse("found", true, outlayRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch", true, outlayRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!outlayRepository.existsById(id)) return new ApiResponse("Outlay not found", false);
        outlayRepository.deleteById(id);
        return new ApiResponse("Outlay deleted", true);
    }

    public ApiResponse deleteAll() {
        outlayRepository.deleteAll();
        return new ApiResponse("Outlays removed", true);
    }

    public ApiResponse getByDate(Date date , Integer branch_id) {
        List<Outlay> allByDate = outlayRepository.findAllByDate(date,branch_id);
        if (allByDate.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByDate);
    }

    public ApiResponse getAllByBranchId(Integer branch_id) {
        List<Outlay> allByBranch_id = outlayRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBranch_id);
    }

    public ApiResponse getAllByBusinessId(Integer businessId) {
        List<Outlay> allByBusinessId = outlayRepository.findAllByBusinessId(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBusinessId);
    }
}
