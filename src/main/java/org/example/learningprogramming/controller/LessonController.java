package org.example.learningprogramming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.learningprogramming.model.Lesson;
import org.example.learningprogramming.model.dto.ResponseMessage;
import org.example.learningprogramming.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lessons")
@Tag(name = "Lesson", description = "Get lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Operation(summary = "Get lessons by course id")
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseMessage> getLessonsByCourseId(@PathVariable Long courseId) {
        try {
            List<Lesson> lessons = lessonService.getLessonsByCourseId(courseId);

            if(lessons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Lessons not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("Lessons found", lessons));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Lessons not retrieved", null));
        }
    }

    @Operation(summary = "Get lesson by id")
    @GetMapping("/{lessonId}")
    public ResponseEntity<ResponseMessage> getLessonById(@PathVariable Long lessonId) {
        try {
            Optional<Lesson> lesson = lessonService.getLessonById(lessonId);

            if(lesson == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Lesson not found", null));
            }

            return ResponseEntity.ok(new ResponseMessage("Lesson found", lesson));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Lesson not retrieved", null));
        }
    }
}
