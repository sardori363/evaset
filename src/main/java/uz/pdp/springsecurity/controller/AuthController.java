package uz.pdp.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.LoginDto;
import uz.pdp.springsecurity.security.JwtProvider;
import uz.pdp.springsecurity.service.AuthService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class    AuthController {
    @Autowired
    AuthService authService;

    final
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * LOGIN YO'LI
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User principal = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(principal.getUsername(), principal.getRole());
        return ResponseEntity.status(200).body(new ApiResponse(token,true,principal));
    }
}
