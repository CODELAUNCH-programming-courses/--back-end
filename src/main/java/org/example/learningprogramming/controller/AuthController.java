package org.example.learningprogramming.controller;

import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.RegisterRequest;
import org.example.learningprogramming.repository.UserRepository;
import org.example.learningprogramming.utils.JwtResponse;
import org.example.learningprogramming.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthController(JwtUtil jwtUtil,UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = new User();
            user.setUserName(registerRequest.getUserName());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());

            String jwt = jwtUtil.generateToken(user.getUserName());

            userRepository.save(user);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            e.printStackTrace();  // Вивести деталі помилки
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Логін
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        User user = userRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new RuntimeException("Error: User not found!"));
//
//        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            return ResponseEntity.badRequest().body("Error: Invalid password!");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail());
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
}
