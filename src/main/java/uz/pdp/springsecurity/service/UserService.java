package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.UserDto;
import uz.pdp.springsecurity.repository.UserRepository;

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
}
