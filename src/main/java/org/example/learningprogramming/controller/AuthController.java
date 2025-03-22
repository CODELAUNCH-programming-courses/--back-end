package org.example.learningprogramming.controller;

import org.example.learningprogramming.model.User;
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
    public ResponseEntity register(@RequestBody User user) {


        try {
            // Кодуємо пароль
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            String jwt = jwtUtil.generateToken(user.getUserName());

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            e.printStackTrace();  // Вивести деталі помилки
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/public/welcome")
    public String publicEndpoint() {
        return "This is a public endpoint. No authentication required!";
    }

    @GetMapping("/private/hello")
    public String privateEndpoint() {
        return "This is a protected endpoint. Authentication required!";
    }
}
