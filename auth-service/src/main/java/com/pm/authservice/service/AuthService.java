package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.dto.SignupRequestDTO;
import com.pm.authservice.dto.SignupResponseDTO;
import com.pm.authservice.exception.UserEmailExistsException;
import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import com.pm.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),
                        u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException | SignatureException e) {
            return false;
        }
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        if (userRepository.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new UserEmailExistsException("User with this email already exists" + signupRequestDTO.getEmail());
        }
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setRole(signupRequestDTO.getRole());
        userRepository.save(user);
        SignupResponseDTO response = new SignupResponseDTO();
        response.setEmail(user.getEmail());
        response.setMessage("User successfully signed in");
        return response;
    }
}
