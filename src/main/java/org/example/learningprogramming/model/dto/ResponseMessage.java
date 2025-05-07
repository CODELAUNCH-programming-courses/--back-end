package org.example.learningprogramming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Генерує геттери, сеттери, toString(), equals() та hashCode()
@AllArgsConstructor // Генерує конструктор із усіма аргументами
@NoArgsConstructor // Генерує безаргументний конструктор
public class ResponseMessage {
    private String message;
    private Object data;
}
