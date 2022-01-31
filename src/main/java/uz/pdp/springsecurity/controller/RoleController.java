package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.RoleDto;
import uz.pdp.springsecurity.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @CheckPermission("ADD_ROLE")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.add(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.edit(id, roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ROLE")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = roleService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_ROLE")
    @GetMapping
    public HttpEntity<?> all() {
        ApiResponse apiResponse = roleService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @CheckPermission("DELETE_ROLE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = roleService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("DELETE_ROLE")
    @DeleteMapping
    public HttpEntity<?> deleteAll() {
        ApiResponse apiResponse = roleService.deleteAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
