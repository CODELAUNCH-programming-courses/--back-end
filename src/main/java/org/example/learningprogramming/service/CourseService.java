package org.example.learningprogramming.service;

import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.repository.projection.course.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {
    List<CourseCardProjection> getAllCourseCards();
    List<CourseCardProjection> getLatestCourseCards();
    Optional<Course> getCourseById(Long id);
}
