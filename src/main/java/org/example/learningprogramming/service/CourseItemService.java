package org.example.learningprogramming.service;

import org.example.learningprogramming.model.CourseItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseItemService {
    List<CourseItem> getAllCourseItems();
    List<CourseItem> getLatestCourseItems();
}
