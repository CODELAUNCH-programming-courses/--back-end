package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.example.learningprogramming.model.Course;
import org.example.learningprogramming.model.dto.CourseDTO;
import org.example.learningprogramming.model.dto.LessonDTO;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.repository.projection.course.CourseCardProjection;
import org.example.learningprogramming.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Course", description = "Get courses")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @Operation(summary = "Дані про всі курси", description = "Отримання даних про всі курси")
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllCourseCards() {
        try{
            List<CourseCardProjection> courses = service.getAllCourseCards();

            if(courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Courses not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("Courses found", courses));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Courses not retrieved", null));
        }
    }

    @Operation(summary = "Дані про всі останньо завантажені курси", description = "Отримання даних про всіх останніх курсів")
    @GetMapping("/latest")
    public ResponseEntity<ResponseMessage> getLatestCourseCards() {
        try {
            List<CourseCardProjection> courses = service.getLatestCourseCards();

            if(courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Courses not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("Courses found", courses));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Courses not retrieved", null));
        }
    }

    @Operation(summary = "Дані про курс", description = "Отримання даних про курс")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getCourseById(@PathVariable Long id) {
        try {
            Optional<Course> courseOptional = service.getCourseById(id);

            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();

                List<LessonDTO> lessonDTOs = course.getLessons().stream()
                        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName()))
                        .toList();

                CourseDTO courseDTO = new CourseDTO(
                        course.getId(),
                        course.getName(),
                        course.getDescription(),
                        course.getLevel(),
                        course.getCreatedAt(),
                        course.getVideoUrl(),
                        lessonDTOs
                );

                return ResponseEntity.ok(new ResponseMessage("Course found", courseDTO));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Courses not found", null));

        } catch(Exception e) {
            e.printStackTrace();  // Вивести деталі помилки
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Courses not retrieved", null));
        }
    }

    @Operation(summary = "Дані про курси за важкістю", description = "Отримання даних про курси")
    @GetMapping("/levels/{levelName}")
    public ResponseEntity<ResponseMessage> getCoursesByLevel(@PathVariable String levelName) {
        try {
            List<CourseCardProjection> courses = service.getCoursesByLevel(levelName);

            if(courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Courses not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("Courses found", courses));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Courses not retrieved", null));
        }
    }
}
