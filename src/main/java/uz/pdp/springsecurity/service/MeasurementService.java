package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Measurement;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.MeasurementDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(MeasurementDto measurementDto) {
        Optional<Branch> optionalBranch = branchRepository.findById(measurementDto.getBranchId());

        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }
        Measurement measurement = new Measurement(
                measurementDto.getName(),
                optionalBranch.get()
        );
        measurementRepository.save(measurement);
        return new ApiResponse("Measurement saved", true);
    }

    public ApiResponse edit(Integer id, MeasurementDto measurementDto) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("Measurement not found", false);

        Measurement measurement = measurementRepository.getById(id);
        measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        return new ApiResponse("edited", true);

    }

    public ApiResponse get(Integer id) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("Measurement not found", false);

        return new ApiResponse("found", true, measurementRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch", true, measurementRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!measurementRepository.existsById(id)) return new ApiResponse("Measurement not found", false);

        measurementRepository.deleteById(id);
        return new ApiResponse("deleted", true);
    }

    public ApiResponse deleteAll() {
        measurementRepository.deleteAll();
        return new ApiResponse("measurements removed", true);
    }
}
