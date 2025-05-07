package org.example.learningprogramming.repository;

import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.repository.projection.course.CourseCardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<CourseCardProjection> findAllBy();
    List<CourseCardProjection> findAllByLevel(String levelName);
}
