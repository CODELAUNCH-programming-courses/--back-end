package org.example.learningprogramming.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.learningprogramming.model.User;

@Data
public class AuthResponse {
    private String token;

    private RegisterResponse user;

    public AuthResponse(String token, User user) {
        this.token = token;

        this.user = new RegisterResponse();
        this.user.setId(user.getId());
        this.user.setEmail(user.getEmail());
        this.user.setUserName(user.getUserName());
        this.user.setTariff(user.getTariff());
    }
}
