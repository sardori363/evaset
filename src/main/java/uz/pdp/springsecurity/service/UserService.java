package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProfileDto;
import uz.pdp.springsecurity.payload.UserDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.BusinessRepository;
import uz.pdp.springsecurity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BusinessRepository businessRepository;

    public ApiResponse add(UserDto userDto) {
        boolean b = userRepository.existsByUsername(userDto.getUsername());
        if (b) return new ApiResponse("User already exist", false);

        ApiResponse response = roleService.get((int) userDto.getRoleId().longValue());
        if (!response.isSuccess())
            return response;

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole((Role) response.getObject());

        Optional<Branch> optionalBranch = branchRepository.findById(userDto.getBranchId());
        if (optionalBranch.isEmpty()) return new ApiResponse("Branch not found", false);
        user.setBranch(optionalBranch.get());

        if (!businessRepository.existsById(userDto.getBusinessId()))
            return new ApiResponse("business not found", false);
        user.setBusiness(businessRepository.findById(userDto.getBusinessId()).get());

        user.setEnabled(userDto.getEnabled());
        userRepository.save(user);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);
        boolean b = userRepository.existsByUsername(userDto.getUsername());

        if (b) return new ApiResponse("Username already exist", false);

        ApiResponse response = roleService.get((int) userDto.getRoleId().longValue());
        if (!response.isSuccess())
            return response;

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Optional<Branch> optionalBranch = branchRepository.findById(userDto.getBranchId());
        if (optionalBranch.isEmpty()) return new ApiResponse("Branch not found", false);
        user.setBranch(optionalBranch.get());

        if (!businessRepository.existsById(userDto.getBusinessId()))
            return new ApiResponse("business not found", false);
        user.setBusiness(businessRepository.findById(userDto.getBusinessId()).get());

        user.setRole((Role) response.getObject());
        user.setEnabled(userDto.getEnabled());

        userRepository.save(user);
        return new ApiResponse("edited", true);
    }

    public ApiResponse get(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) return new ApiResponse("NOT FOUND", false);

        return new ApiResponse("FOUND", true, userRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        List<User> userRepositoryAll = userRepository.findAll();
        return new ApiResponse(true, userRepositoryAll);
    }

    public ApiResponse delete(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("User not found", false);
        userRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse deleteAll() {
        userRepository.deleteAll();
        return new ApiResponse("users removed", true);
    }

    public ApiResponse editMyProfile(ProfileDto profileDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.existsByUsernameAndIdNot(profileDto.getUserName(), user.getId()))
            return new ApiResponse("Username already exists!", false);

        if (!profileDto.getPassword().equals(profileDto.getPrePassword()))
            return new ApiResponse("Passwords are not compatible!", false);


        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setUsername(profileDto.getUserName());
        user.setPassword(passwordEncoder.encode(profileDto.getPassword()));

        userRepository.save(user);
        return new ApiResponse("User saved!", true);
    }

    public ApiResponse getByRole(Integer role_id) {
        List<User> allByRole_id = userRepository.findAllByRole_Id(role_id);
        if (allByRole_id.isEmpty()) return new ApiResponse("not found", false);

        return new ApiResponse("found", true, allByRole_id);
    }

    public ApiResponse getAllByBusinessId(Integer business_id) {
        List<User> allByBusiness_id = userRepository.findAllByBusiness_Id(business_id);
        if (allByBusiness_id.isEmpty()) return new ApiResponse("business not found", false);
        return new ApiResponse("found", true, allByBusiness_id);
    }

    public ApiResponse getOneByBusinessId(Integer business_id, Integer id) {
        Optional<User> byBusiness_idAndId = userRepository.findByBusiness_IdAndId(business_id, id);
        if (byBusiness_idAndId.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, byBusiness_idAndId);
    }

    public ApiResponse getAllByBranchId(Integer branch_id) {
        List<User> allByBranch_id = userRepository.findAllByBranch_Id(branch_id);
        if (allByBranch_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByBranch_id);
    }

    public ApiResponse getOneByBranchId(Integer branch_id, Integer id) {
        Optional<User> byBranch_idAndId = userRepository.findByBranch_IdAndId(branch_id, id);
        if (byBranch_idAndId.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, byBranch_idAndId);
    }

    public ApiResponse getAllByBusinessAndBranch(Integer business_id, Integer branch_id) {
        List<User> allByBusiness_idAndBranch_id = userRepository.findAllByBusiness_IdAndBranch_Id(business_id, branch_id);
        if (allByBusiness_idAndBranch_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByBusiness_idAndBranch_id);
    }

    public ApiResponse getOneByBusinessAndBranch(Integer business_id, Integer branch_id, Integer id) {
        Optional<User> byBusiness_idAndBranch_idAndId = userRepository.findByBusiness_IdAndBranch_IdAndId(business_id, branch_id, id);
        if (byBusiness_idAndBranch_idAndId.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, byBusiness_idAndBranch_idAndId);
    }

    public ApiResponse deleteOneByBusinessIdAndId(Integer business_id, Integer id) {
        Optional<User> byBusiness_idAndId = userRepository.findByBusiness_IdAndId(business_id, id);
        if (byBusiness_idAndId.isEmpty()) return new ApiResponse("not found",false);

        userRepository.deleteByBusiness_IdAndId(business_id,id);
        return new ApiResponse("deleted",true);
    }
}
