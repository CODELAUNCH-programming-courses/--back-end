package org.example.learningprogramming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDTO {
    private Long id;

    private String name;
    private String description;
    private String level;
    private LocalDateTime createdAt;
    private String videoUrl;

    private List<LessonDTO> lessons;
}
