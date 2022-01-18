package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ProfileDto;
import uz.pdp.springsecurity.payload.UserDto;
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
        user.setEnabled(userDto.getEnabled());
        userRepository.save(user);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);
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
        if (!byId.isPresent()) return new ApiResponse("User not found", false);
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
}
