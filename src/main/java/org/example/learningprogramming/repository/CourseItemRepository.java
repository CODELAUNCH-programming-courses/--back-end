package org.example.learningprogramming.repository;

import org.example.learningprogramming.model.CourseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseItemRepository extends JpaRepository<CourseItem, Long> {
}
