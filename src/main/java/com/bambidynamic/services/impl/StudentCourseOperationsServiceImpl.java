package com.bambidynamic.services.impl;

import com.bambidynamic.entity.StudentCourse;
import com.bambidynamic.repositories.StudentCourseRepository;
import com.bambidynamic.services.CourseService;
import com.bambidynamic.services.StudentCourseOperationsService;
import com.bambidynamic.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class StudentCourseOperationsServiceImpl implements StudentCourseOperationsService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Override
    public void applyForCourse(Long studentId, Long courseId) {
        var student = studentService.findById(studentId);
        var course = courseService.findById(courseId);

        var studentCourse = studentCourseRepository.findByCourseAndStudent(course, student);
        if (studentCourse != null) {
            log.info("Student with ID {} is already recorded on course with ID {}", studentId, courseId);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Student is already present on the course specified");
        }

        studentCourse = StudentCourse.builder()
                .course(course)
                .student(student)
                .build();

        studentCourseRepository.save(studentCourse);
        log.info("Student with ID {} has successfully applied for course with ID {}", studentId, courseId);
    }

    @Override
    public void removeStudentFromCourse(Long studentId, Long courseId) {
        studentCourseRepository.deleteByCourse_IdAndStudent_Id(courseId, studentId);
        log.info("Student with ID {} was successfully removed from Course with ID {}", studentId, courseId);
    }

}
