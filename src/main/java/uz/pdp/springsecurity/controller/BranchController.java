package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.aotations.CheckPermission;
import uz.pdp.springsecurity.payload.AddressDto;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BranchDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.service.BranchService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchService branchService;

    @CheckPermission("ADD_BRANCH")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody BranchDto branchDto) {
        ApiResponse apiResponse = branchService.addBranch(branchDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_BRANCH")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody BranchDto branchDto) {
        ApiResponse apiResponse = branchService.editBranch(id, branchDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_BRANCH")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = branchService.getBranch(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_BRANCH")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = branchService.getBranches();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_BRANCH")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = branchService.deleteBranch(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_BRANCH")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = branchService.deleteBranches();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
