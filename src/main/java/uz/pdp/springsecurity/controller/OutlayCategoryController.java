package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.MeasurementDto;
import uz.pdp.springsecurity.payload.OutlayCategoryDto;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;
import uz.pdp.springsecurity.service.OutlayCategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/outlay")
public class OutlayCategoryController {
    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    @Autowired
    OutlayCategoryService outlayCategoryService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody OutlayCategoryDto outlayCategoryDto) {
        ApiResponse apiResponse = outlayCategoryService.add(outlayCategoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OutlayCategoryDto outlayCategoryDto) {
        ApiResponse apiResponse = outlayCategoryService.edit(id, outlayCategoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayCategoryService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = outlayCategoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayCategoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = outlayCategoryService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
