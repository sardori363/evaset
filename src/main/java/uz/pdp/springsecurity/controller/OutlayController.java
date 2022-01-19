package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.OutlayDto;
import uz.pdp.springsecurity.service.OutlayService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/outlay")
public class OutlayController {

    @Autowired
    OutlayService outlayService;

//    @CheckPermission("ADD_OUTLAY")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody OutlayDto outlayDto) {
        ApiResponse apiResponse = outlayService.add(outlayDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_OUTLAY")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OutlayDto outlayDto) {
        ApiResponse apiResponse = outlayService.edit(id, outlayDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = outlayService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = outlayService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_OUTLAY")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = outlayService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
