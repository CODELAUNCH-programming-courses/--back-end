package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.repository.projection.course.CourseCardProjection;
import org.example.learningprogramming.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@Tag(name = "Дані про курси", description = "Отримання інформації про курси")
@AllArgsConstructor()
public class CourseController {
    private final CourseService service;

    @Operation(summary = "Дані про всі курси", description = "Отримання даних про всі курси")
    @GetMapping("/all")
    public List<CourseCardProjection> getAllCourseCards() {
        return service.getAllCourseCards();
    }

    @Operation(summary = "Дані про всі останньо завантажені курси", description = "Отримання даних про всіх останніх курсів")
    @GetMapping("/latest")
    public List<CourseCardProjection> getLatestCourseCards() {
        return service.getLatestCourseCards();
    }

//    @Operation(summary = "Дані про курс", description = "Отримання даних про курс")
//    @GetMapping("/:id")
//    public Course getCourseById() {
//        return 0;
//    }
}
