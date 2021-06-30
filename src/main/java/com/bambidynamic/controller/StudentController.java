package com.bambidynamic.controller;

import com.bambidynamic.dto.StudentCourseResponse;
import com.bambidynamic.dto.StudentRequest;
import com.bambidynamic.entity.Student;
import com.bambidynamic.services.StudentCourseOperationsService;
import com.bambidynamic.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentCourseOperationsService studentCourseOperationsService;

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }


    @PostMapping("/students")
    public Student createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }


    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable("id") Long studentId, @RequestBody StudentRequest request) {
        studentService.updateStudent(studentId, request);
    }

    @PutMapping("/students/{studentId}/courses/{courseId}")
    public void applyForCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        studentCourseOperationsService.applyForCourse(studentId, courseId);
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public void removeStudentFromCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        studentCourseOperationsService.removeStudentFromCourse(studentId, courseId);
    }

    @GetMapping("/students/{id}/courses")
    public StudentCourseResponse getStudentsCourses(@PathVariable("id") Long studentId) {
        return studentService.getCoursesByStudentId(studentId);
    }
}
