package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.auth.AuthRequest;
import org.example.learningprogramming.model.dto.auth.AuthResponse;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.model.dto.user.UpdateUserRequestDTO;
import org.example.learningprogramming.repository.UserRepository;
import org.example.learningprogramming.service.UserService;
import org.example.learningprogramming.utils.JwtResponse;
import org.example.learningprogramming.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllUsers() {
        try {
        List<User> users = this.userService.getAllUsers();

        return ResponseEntity.ok(new ResponseMessage("Users retrieved", users));
        } catch(ResponseStatusException e) {
            e.printStackTrace();  // Вивести деталі помилки
            return ResponseEntity.status(e.getStatusCode()).body(new ResponseMessage("Internal server error", e.getReason()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> userRegister(@RequestBody AuthRequest registerRequest) {
        try {
            AuthResponse authResponse = this.userService.userRegister(registerRequest.getEmail(), registerRequest.getPassword());

            return ResponseEntity.ok(new ResponseMessage("User saved", authResponse));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("User not saved", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> userLogin(@RequestBody AuthRequest loginRequest) {
        try {
            AuthResponse authResponse = this.userService.userLogin(loginRequest.getEmail(), loginRequest.getPassword());

            return ResponseEntity.ok(new ResponseMessage("User is logged in", authResponse));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new ResponseMessage("User is not logged in", e.getReason()));
        }
    }


    @PatchMapping()
    public ResponseEntity<ResponseMessage> updateUser(@RequestBody UpdateUserRequestDTO updateRequestData) {
        try {
            User user = this.userService.updateUser(updateRequestData);

            if(user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("User updated", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("User is not update", e.getMessage()));
        }
    }
}
