package org.example.learningprogramming.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
    @AllArgsConstructor
public class UpdateUserRequestDTO {
    private Long id;

    private String username;

    private String email;
}
