package org.example.learningprogramming.service.impl;

import lombok.AllArgsConstructor;
import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.repository.CourseRepository;
import org.example.learningprogramming.repository.projection.course.*;
import org.example.learningprogramming.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CourseServiceImpl implements CourseService {
   private final CourseRepository repository;

   @Autowired
   public CourseServiceImpl(CourseRepository repository) {
       this.repository = repository;
   }

    @Override
    public List<CourseCardProjection> getAllCourseCards() {
        return repository.findAllBy();
    };

    @Override
    public List<CourseCardProjection> getLatestCourseCards() {
        List<CourseCardProjection> courseItems = repository.findAllBy();

        return courseItems.stream()
                .filter(item -> item.getCreatedAt() != null)
                .sorted(Comparator.comparing(CourseCardProjection::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
    };

    @Override
    public Optional<Course> getCourseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CourseCardProjection> getCoursesByLevel(String levelName) {
        return this.repository.findAllByLevel(levelName);
    }
}
