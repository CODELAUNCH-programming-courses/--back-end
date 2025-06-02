package org.example.learningprogramming.model.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.learningprogramming.model.dto.lesson.LessonDTO;

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
