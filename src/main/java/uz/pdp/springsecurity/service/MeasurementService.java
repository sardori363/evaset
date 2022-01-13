package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Measurement;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.MeasurementDto;
import uz.pdp.springsecurity.repository.MeasurementRepository;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse add(MeasurementDto measurementDto) {
        Measurement measurement = new Measurement(
                measurementDto.getName()
        );
        measurementRepository.save(measurement);
        return new ApiResponse("Measurement saved",);
    }
}
