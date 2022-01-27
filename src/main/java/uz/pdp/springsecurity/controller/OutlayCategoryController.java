package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayCategoryDto;
import uz.pdp.springsecurity.repository.OutlayCategoryRepository;
import uz.pdp.springsecurity.service.OutlayCategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/outlayCategory")
public class OutlayCategoryController {
    @Autowired
    OutlayCategoryRepository outlayCategoryRepository;

    @Autowired
    OutlayCategoryService outlayCategoryService;

    @CheckPermission("ADD_OUTLAY")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody OutlayCategoryDto outlayCategoryDto) {
        ApiResponse apiResponse = outlayCategoryService.add(outlayCategoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_OUTLAY")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OutlayCategoryDto outlayCategoryDto) {
        ApiResponse apiResponse = outlayCategoryService.edit(id, outlayCategoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayCategoryService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = outlayCategoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_OUTLAY")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayCategoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_OUTLAY")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = outlayCategoryService.deleteAll ();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
