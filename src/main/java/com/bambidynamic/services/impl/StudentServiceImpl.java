package com.bambidynamic.services.impl;

import com.bambidynamic.dto.StudentCourseResponse;
import com.bambidynamic.dto.StudentRequest;
import com.bambidynamic.entity.Student;
import com.bambidynamic.repositories.CourseRepository;
import com.bambidynamic.repositories.StudentCourseRepository;
import com.bambidynamic.repositories.StudentRepository;
import com.bambidynamic.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;


    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No student found for ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No student found with the ID specified");
                });
    }


    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    public Student createStudent(StudentRequest request) {
        var student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .city(request.getCity())
                .street(request.getStreet())
                .faculty(request.getFaculty())
                .dateOfBirth(request.getDateOfBirth())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Long studentId, StudentRequest request) {
        var updatedStudent = studentRepository.findById(studentId)
                .map(student -> {
                    if (request.getFirstName() != null) {
                        student.setFirstName(request.getFirstName());
                    }

                    if (request.getLastName() != null) {
                        student.setLastName(request.getLastName());
                    }

                    if (request.getCity() != null) {
                        student.setCity(request.getCity());
                    }

                    if (request.getStreet() != null) {
                        student.setStreet(request.getStreet());
                    }

                    if (request.getFaculty() != null) {
                        student.setFaculty(request.getFaculty());
                    }

                    if (request.getDateOfBirth() != null) {
                        student.setDateOfBirth(request.getDateOfBirth());
                    }


                    return studentRepository.save(student);
                })
                .orElseThrow(() -> {
                    log.error("Update failed. No student found for ID: {}", studentId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No student found with the ID specified");
                });
        log.info("Updated student: {}", updatedStudent);
    }

    @Override
    public void deleteById(Long studentId) {
        studentCourseRepository.deleteAllByStudent_Id(studentId);

        // deleteAll bypasses "if exists" check (+extra request to DB) and prevents from exception raise on deleting a non-existent entity
        studentRepository.deleteAllById(studentId);
        log.info("Deleted student with ID {}", studentId);
    }



    @Override
    public StudentCourseResponse getCoursesByStudentId(Long studentId) {
        var courses = courseRepository.getAllCoursesByStudentId(studentId);
        return StudentCourseResponse.builder()
                .courses(courses)
                .studentId(studentId)
                .build();
    }
}
