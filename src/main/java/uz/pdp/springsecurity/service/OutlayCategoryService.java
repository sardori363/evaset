package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.OutlayCategory;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayCategoryDto;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;
import uz.pdp.springsecurity.repository.OutlayRepository;

@Service
public class OutlayCategoryService {
    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    public ApiResponse add(OutlayCategoryDto outlayCategoryDto) {
        OutlayCategory outlayCategory = new OutlayCategory(
                outlayCategoryDto.getTitle(),
                outlayCategoryDto.getSumma()
        );
        outlayCategoryRepository.save(outlayCategory);
        return new ApiResponse("Saved",true);
    }

    public ApiResponse edit(Integer id, OutlayCategoryDto outlayCategoryDto) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found",false);

        OutlayCategory outlayCategory = outlayCategoryRepository.getById(id);
        outlayCategory.setTitle(outlayCategoryDto.getTitle());
        outlayCategory.setSumma(outlayCategoryDto.getSumma());

        outlayCategoryRepository.save(outlayCategory);
        return new ApiResponse("Outlay category updated",true);
    }

    public ApiResponse get(Integer id) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found",false);
        return new ApiResponse("found",true,outlayCategoryRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,outlayCategoryRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!outlayCategoryRepository.existsById(id)) return new ApiResponse("Outlay category not found",false);
        outlayCategoryRepository.deleteById(id);
        return new ApiResponse("outlay category deleted",true);
    }

    public ApiResponse deleteAll() {
        outlayCategoryRepository.deleteAll();
        return new ApiResponse("outlay categories removed",true);
    }
}
