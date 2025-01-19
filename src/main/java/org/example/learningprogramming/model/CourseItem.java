package org.example.learningprogramming.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_items")
public class CourseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;
    private String imageUrl;

    private LocalDateTime createdAt;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
