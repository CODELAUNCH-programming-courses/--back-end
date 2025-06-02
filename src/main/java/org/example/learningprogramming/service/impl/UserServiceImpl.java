package org.example.learningprogramming.service.impl;

import org.example.learningprogramming.model.Tariff;
import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.model.dto.auth.AuthResponse;
import org.example.learningprogramming.model.dto.user.UpdateUserRequestDTO;
import org.example.learningprogramming.repository.UserRepository;
import org.example.learningprogramming.service.UserService;
import org.example.learningprogramming.utils.JwtResponse;
import org.example.learningprogramming.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository =  userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found", null);
        }

        return users;
    }

    @Override
    public AuthResponse userRegister(String userEmail, String userPassword) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userPassword));
        user.setEmail(userEmail);

        String jwt = jwtUtil.generateToken(user.getEmail());

        userRepository.save(user);

        return new AuthResponse(new JwtResponse(jwt).getToken(), user);
    }

    @Override
    public AuthResponse userLogin(String userEmail, String userPassword) {
        User user = userRepository.findByEmail(userEmail);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (!this.passwordEncoder.matches(userPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data is not valid");
        }

        String jwt = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(new JwtResponse(jwt).getToken(), user);
    }

    @Override
    public User updateUser(UpdateUserRequestDTO userData) {
        Optional<User> userOptional = this.userRepository.findById(userData.getId());

        if(userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User userWithEmail = this.userRepository.findByEmail(userData.getEmail());
        if(userWithEmail != null && !userWithEmail.getId().equals(userData.getId())) {
            throw new RuntimeException("Email already in use by another user");
        }

        User user = userOptional.get();

        user.setEmail(userData.getEmail());
        user.setUserName(userData.getUsername());

        return userRepository.save(user);
    }

    @Override
    public User setUserTariff(Long userId, Tariff tariff) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }

        User user = userOptional.get();
        user.setTariff(tariff);

        return userRepository.save(user);
    }
}
