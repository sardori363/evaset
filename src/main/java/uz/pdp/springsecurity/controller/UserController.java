package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProfileDto;
import uz.pdp.springsecurity.payload.UserDto;
import uz.pdp.springsecurity.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @CheckPermission("ADD_USER")
    @PostMapping()
    public HttpEntity<?> add(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.add(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("EDIT_USER")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.edit(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @CheckPermission("VIEW_USER")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_USER")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = userService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @CheckPermission("DELETE_USER")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    @CheckPermission("EDIT_MY_PROFILE")
    @PutMapping
    public ResponseEntity<?> editMyProfile(@Valid @RequestBody ProfileDto profileDto) {
        ApiResponse apiResponse = userService.editMyProfile(profileDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @CheckPermission("VIEW_USER")
    @GetMapping("/get-by-role/{role_id}")
    public HttpEntity<?> getByRole(@PathVariable Integer role_id) {
        ApiResponse apiResponse = userService.getByRole(role_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_USER")
    @GetMapping("/{business_id}")
    public HttpEntity<?> getAllByBusinessId(@PathVariable Integer business_id) {
        ApiResponse apiResponse = userService.getAllByBusinessId(business_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("VIEW_USER")
    @GetMapping("/{branch_id}")
    public HttpEntity<?> getAllByBranchId(@PathVariable Integer branch_id) {
        ApiResponse apiResponse = userService.getAllByBranchId(branch_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
