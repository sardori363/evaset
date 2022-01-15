package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Outlay;
import uz.pdp.springsecurity.entity.OutlayCategory;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayDto;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;
import uz.pdp.springsecurity.repository.OutlayRepository;

import java.util.Optional;

@Service
public class OutlayService {
    @Autowired
    OutlayRepository outlayRepository;

    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    public ApiResponse add(OutlayDto outlayDto) {
        Outlay outlay = new Outlay();
        outlay.setName(outlayDto.getName());

        Optional<OutlayCategory> optionalCategory = outlayCategoryRepository.findById(outlayDto.getOutlayCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Outlay category not found",false);

        outlay.setOutlayCategoryId(optionalCategory.get());
        outlayRepository.save(outlay);
        return new ApiResponse("Outlay saved",true);
    }

    public ApiResponse edit(Integer id, OutlayDto outlayDto) {
        if (!outlayRepository.existsById(id)) return new ApiResponse("Outlay not found",false);

        Outlay outlay = outlayRepository.getById(id);
        outlay.setName(outlayDto.getName());

        Optional<OutlayCategory> optionalCategory = outlayCategoryRepository.findById(outlayDto.getOutlayCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Outlay category not found",false);

        outlay.setOutlayCategoryId(optionalCategory.get());
        outlayRepository.save(outlay);
        return new ApiResponse("Outlay updated",true);
    }
}
