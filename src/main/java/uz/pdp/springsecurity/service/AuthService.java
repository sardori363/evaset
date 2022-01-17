package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.enums.Permissions;
import uz.pdp.springsecurity.exeptions.RescuersNotFoundEx;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.RegisterDto;
import uz.pdp.springsecurity.repository.RoleRepository;
import uz.pdp.springsecurity.repository.UserRepository;

@Service
public class  AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername()))
            return new ApiResponse("Username already exists!", false);

        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Passwors are not compatible!", false);
        User user = new User(
                registerDto.getFirstName(),
                registerDto.getUsername(),
                registerDto.getLastName(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(String.valueOf(Permissions.USER)).orElseThrow(() -> new RescuersNotFoundEx("FIND USER","NOT FOUND",Permissions.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("User saved!", true);
    }


    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByFirstName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
