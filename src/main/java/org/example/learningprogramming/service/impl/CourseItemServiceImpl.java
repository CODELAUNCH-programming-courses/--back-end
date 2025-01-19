package org.example.learningprogramming.service.impl;

import lombok.AllArgsConstructor;
import org.example.learningprogramming.model.CourseItem;
import org.example.learningprogramming.repository.CourseItemRepository;
import org.example.learningprogramming.service.CourseItemService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class CourseItemServiceImpl implements CourseItemService {
   private  final CourseItemRepository repository;

    @Override
    public List<CourseItem> getAllCourseItems() {
        return repository.findAll();
    };

    @Override
    public List<CourseItem> getLatestCourseItems() {
        List<CourseItem> courseItems = repository.findAll();

        return courseItems.stream()
                .filter(item -> item.getCreatedAt() != null) // Перевіряємо, щоб поле не було null
                .sorted(Comparator.comparing(CourseItem::getCreatedAt).reversed()) // Сортуємо по даті
                .limit(5) // Беремо лише перші 5
                .collect(Collectors.toList());
    };
}
