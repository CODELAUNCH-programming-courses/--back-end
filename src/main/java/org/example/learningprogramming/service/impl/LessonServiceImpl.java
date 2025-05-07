package org.example.learningprogramming.service.impl;

import org.example.learningprogramming.model.Lesson;
import org.example.learningprogramming.repository.LessonRepository;
import org.example.learningprogramming.service.LessonService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    @Override
    public Optional<Lesson> getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId);
    }
}
