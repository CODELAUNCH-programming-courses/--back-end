package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.repository.projection.course.CourseCardProjection;
import org.example.learningprogramming.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Дані про курси", description = "Отримання інформації про курси")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @Operation(summary = "Дані про всі курси", description = "Отримання даних про всі курси")
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllCourseCards() {
        List<CourseCardProjection> courses = service.getAllCourseCards();

        if(!courses.isEmpty()) {
            return ResponseEntity.ok(new ResponseMessage("Courses found", courses, "success"));
        } else {
            ResponseMessage response = new ResponseMessage();
            response.setMessage("Courses not found");
            response.setStatus("failed");
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Дані про всі останньо завантажені курси", description = "Отримання даних про всіх останніх курсів")
    @GetMapping("/latest")
    public ResponseEntity<ResponseMessage> getLatestCourseCards() {
        List<CourseCardProjection> courses = service.getLatestCourseCards();

        if(!courses.isEmpty()) {
            return ResponseEntity.ok(new ResponseMessage("Courses found", courses, "success"));
        } else {
            ResponseMessage response = new ResponseMessage();
            response.setMessage("Courses not found");
            response.setStatus("failed");
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Дані про курс", description = "Отримання даних про курс")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getCourseById(@PathVariable Long id) {
        Optional<Course> course = service.getCourseById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(new ResponseMessage("Course found", course.get(), "success"));
        } else {
            ResponseMessage response = new ResponseMessage();
            response.setMessage("Course not found");
            response.setStatus("failed");
            return ResponseEntity.status(404).body(response);
        }
    }
}
