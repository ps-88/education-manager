package com.bambidynamic.controller;

import com.bambidynamic.dto.CourseRequest;
import com.bambidynamic.dto.CourseStudentResponse;
import com.bambidynamic.dto.CoursesStudentsAggregationResponse;
import com.bambidynamic.entity.Course;
import com.bambidynamic.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/courses")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/courses/{id}")
    public Course findById(@PathVariable("id") Long id) {
        return courseService.findById(id);
    }


    @PostMapping("/courses")
    public Course createCourse(@RequestBody CourseRequest request) {

        return courseService.createCourse(request);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }


    @PutMapping("/courses/{id}")
    public void updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseRequest request) {
        courseService.updateCourse(courseId, request);
    }

    @GetMapping("/courses/{id}/students")
    public CourseStudentResponse getCoursesStudents(@PathVariable("id") Long id) {
        return courseService.getStudentsByCourseId(id);
    }

    @GetMapping("/courses-students")
    public Map<String, Long> getAllCoursesWithStudentNumber(){
        return courseService.getCoursesWithNumberOfStudents();
    }

    @GetMapping("/courses-students-2")
    public List<CoursesStudentsAggregationResponse> getStudentsCountByCourses() {
        return courseService.getStudentsCountByCourses();
    }
}
