package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.MeasurementDto;
import uz.pdp.springsecurity.repository.MeasurementRepository;
import uz.pdp.springsecurity.service.MeasurementService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/meaurement")
public class MeasurementController {
    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MeasurementService measurementService;

    @CheckPermission("ADD_MEASUREMENT")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody MeasurementDto measurementDto) {
        ApiResponse apiResponse = measurementService.add(measurementDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_MEASUREMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody MeasurementDto measurementDto) {
        ApiResponse apiResponse = measurementService.edit(id, measurementDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MEASUREMENT")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = measurementService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_MEASUREMENT")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = measurementService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MEASUREMENT")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = measurementService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_MEASUREMENT")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = measurementService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
