package com.bambidynamic.services;

import com.bambidynamic.dto.StudentCourseResponse;
import com.bambidynamic.dto.StudentRequest;
import com.bambidynamic.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findById(Long id);
    Student createStudent(StudentRequest request);
    void updateStudent(Long studentId, StudentRequest request);
    void deleteById(Long id);
    StudentCourseResponse getCoursesByStudentId(Long studentId);

}