package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.OutlayCategory;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayCategoryDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;

import java.util.Optional;

@Service
public class OutlayCategoryService {
    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(OutlayCategoryDto outlayCategoryDto) {

        Optional<Branch> optionalBranch = branchRepository.findById(outlayCategoryDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }

        OutlayCategory outlayCategory = new OutlayCategory(
                outlayCategoryDto.getTitle(),
                optionalBranch.get()
        );

        outlayCategoryRepository.save(outlayCategory);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, OutlayCategoryDto outlayCategoryDto) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found", false);

        OutlayCategory outlayCategory = outlayCategoryRepository.getById(id);
        outlayCategory.setTitle(outlayCategoryDto.getTitle());

        outlayCategoryRepository.save(outlayCategory);
        return new ApiResponse("Outlay category updated", true);
    }

    public ApiResponse get(Integer id) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found", false);
        return new ApiResponse("found", true, outlayCategoryRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch", true, outlayCategoryRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found", false);
        outlayCategoryRepository.deleteById(id);
        return new ApiResponse("outlay category deleted", true);
    }

    public ApiResponse deleteAll() {
        outlayCategoryRepository.deleteAll();
        return new ApiResponse("outlay categories removed", true);
    }
}
