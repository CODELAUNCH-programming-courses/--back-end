package org.example.learningprogramming.service;

import org.example.learningprogramming.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> getLessonsByCourseId(Long courseId);
    Optional<Lesson> getLessonById(Long lessonId);
}
