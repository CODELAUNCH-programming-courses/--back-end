package org.example.learningprogramming.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "course_card")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String subscriptionType;
    private String level;
    private String imageUrl;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    private List<String> instruments;
    private int numberOfTasks;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalHours() {
        return lessons.stream().mapToInt(Lesson::getDurationMinutes).sum() / 60.0;
    }
}