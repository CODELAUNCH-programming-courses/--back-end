package org.example.learningprogramming.controller;

import lombok.AllArgsConstructor;
import org.example.learningprogramming.model.CourseItem;
import org.example.learningprogramming.service.CourseItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/course-item")
@AllArgsConstructor()
public class CourseItemController {
    private final CourseItemService service;

    @GetMapping
    public List<CourseItem> getAllCourseItems() {
        return service.getAllCourseItems();
    }

    @GetMapping("/latest")
    public List<CourseItem> getLatestCourseItems() {
        return service.getLatestCourseItems();
    }
}
