package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.learningprogramming.model.Tariff;
import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.auth.AuthRequest;
import org.example.learningprogramming.model.dto.auth.AuthResponse;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.model.dto.user.UpdateUserRequestDTO;
import org.example.learningprogramming.service.UserService;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllUsers() {
        try {
        List<User> users = this.userService.getAllUsers();

        return ResponseEntity.ok(new ResponseMessage("Users retrieved", users));
        } catch(ResponseStatusException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode()).body(new ResponseMessage(e.getReason(), null));
        }
    }

    @Operation(summary = "User register")
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> userRegister(@RequestBody AuthRequest registerRequest) {
        try {
            AuthResponse authResponse = this.userService.userRegister(registerRequest.getEmail(), registerRequest.getPassword());

            return ResponseEntity.ok(new ResponseMessage("User saved", authResponse));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode()).body(new ResponseMessage(e.getReason(), null));
        }
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> userLogin(@RequestBody AuthRequest loginRequest) {
        try {
            AuthResponse authResponse = this.userService.userLogin(loginRequest.getEmail(), loginRequest.getPassword());

            return ResponseEntity.ok(new ResponseMessage("User is logged in", authResponse));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode()).body(new ResponseMessage(e.getReason(), null));
        }
    }

    @Operation(summary = "User update")
    @PatchMapping()
    public ResponseEntity<ResponseMessage> updateUser(@RequestBody UpdateUserRequestDTO updateRequestData) {
        try {
            User user = this.userService.updateUser(updateRequestData);

            return ResponseEntity.ok(new ResponseMessage("User updated", user));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ResponseMessage(e.getReason(), null));
        }
    }

    @Operation(summary = "Set user's tariff")
    @PatchMapping("/{userId}/set-tariff")
    public ResponseEntity<ResponseMessage> setUserTariff(@PathVariable Long userId, @RequestParam Tariff tariff) {
        try {
            User user = userService.setUserTariff(userId, tariff);

            return ResponseEntity.ok(new ResponseMessage("User set tariff", user));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ResponseMessage(e.getReason(), null));
        }
    }
}
