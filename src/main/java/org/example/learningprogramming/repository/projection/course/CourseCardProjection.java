package org.example.learningprogramming.repository.projection.course;

import java.time.LocalDateTime;

public interface CourseCardProjection {
    Long getId();
    String getName();
    String getDescription();
    String getSubscriptionType();
    String getImageUrl();
    String getLevel();
    LocalDateTime getCreatedAt();
}
