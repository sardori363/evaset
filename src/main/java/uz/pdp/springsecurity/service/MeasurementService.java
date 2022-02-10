package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Business;
import uz.pdp.springsecurity.entity.Measurement;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.MeasurementDto;
import uz.pdp.springsecurity.repository.BusinessRepository;
import uz.pdp.springsecurity.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse add(MeasurementDto measurementDto) {
        Optional<Business> optionalBusiness = businessRepository.findById(measurementDto.getBusinessId());

        if (optionalBusiness.isEmpty()) {
            return new ApiResponse("BUSINESS NOT FOUND", false);
        }
        Measurement measurement = new Measurement(
                measurementDto.getName(),
                optionalBusiness.get()
        );
        measurementRepository.save(measurement);
        return new ApiResponse("ADDED", true);
    }

    public ApiResponse edit(Integer id, MeasurementDto measurementDto) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);

        Measurement measurement = measurementRepository.getById(id);
        measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        return new ApiResponse("EDITED", true);

    }

    public ApiResponse get(Integer id) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);

        return new ApiResponse("FOUND", true, measurementRepository.findById(id).get());
    }

    public ApiResponse delete(Integer id) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("NOT FOUND", false);

        measurementRepository.deleteById(id);
        return new ApiResponse("DELETED", true);
    }

    public ApiResponse getByBusiness(Integer business_id) {
        List<Measurement> allByBranch_business_id = measurementRepository.findAllByBusiness_Id(business_id);
        if (allByBranch_business_id.isEmpty()) return new ApiResponse("NOT FOUND", false);
        return new ApiResponse("FOUND", true, allByBranch_business_id);
    }
}
