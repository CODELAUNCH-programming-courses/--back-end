package org.example.learningprogramming.controller;

import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.auth.AuthRequest;
import org.example.learningprogramming.model.dto.auth.AuthResponse;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.repository.UserRepository;
import org.example.learningprogramming.utils.JwtResponse;
import org.example.learningprogramming.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil,UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public ResponseMessage getAllUsers() {
        try {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            return new ResponseMessage("Users not found", ResponseEntity.status(HttpStatus.NOT_FOUND), "fail");
        }

        return new ResponseMessage("Users retrieved", users, "success");
        } catch(Exception e) {
            e.printStackTrace();  // Вивести деталі помилки
            return new ResponseMessage("Internal server error", ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR), "fail");
        }
    }

    @PostMapping("/register")
    public ResponseMessage register(@RequestBody AuthRequest registerRequest) {
        try {
            User user = new User();
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());

            String jwt = jwtUtil.generateToken(user.getEmail());

            userRepository.save(user);

            AuthResponse response = new AuthResponse(new JwtResponse(jwt).getToken(), user);

            return new ResponseMessage("User saved", response, "success");
        } catch (Exception e) {
            e.printStackTrace();  // Вивести деталі помилки
            return new ResponseMessage("User not saved", ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"), "fail");
        }
    }

    // Логін
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody AuthRequest loginRequest) {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if(user == null) {
                return new ResponseMessage("User not found", ResponseEntity.status(HttpStatus.NOT_FOUND), "fail");
            }

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new ResponseMessage("Data is not valid", ResponseEntity.status(HttpStatus.BAD_REQUEST), "fail");
            }

            String jwt = jwtUtil.generateToken(user.getEmail());
            AuthResponse response = new AuthResponse(new JwtResponse(jwt).getToken(), user);
            return new ResponseMessage("User is logged in", response, "success");
        } catch (Exception e) {
            return new ResponseMessage("User is not logged in", ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR), "fail");
        }
    }
}
