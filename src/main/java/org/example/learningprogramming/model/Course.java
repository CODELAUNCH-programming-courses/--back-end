package org.example.learningprogramming.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "course")
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
    private String videoUrl;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @ElementCollection
    @CollectionTable(name = "course_instruments", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "instrument")
    private List<String> instruments;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalHours() {
        return lessons.stream().mapToInt(Lesson::getDurationMinutes).sum() / 60.0;
    }
}