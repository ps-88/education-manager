package com.bambidynamic.services.impl;

import com.bambidynamic.dto.CourseRequest;
import com.bambidynamic.dto.CourseStudentResponse;
import com.bambidynamic.dto.CoursesStudentsAggregationResponse;
import com.bambidynamic.entity.Course;
import com.bambidynamic.entity.StudentCourse;
import com.bambidynamic.repositories.CourseRepository;
import com.bambidynamic.repositories.StudentCourseRepository;
import com.bambidynamic.repositories.StudentRepository;
import com.bambidynamic.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No course found for ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No course found with the ID specified");
                });
    }


    @Override
    public void deleteById(Long courseId) {
        studentCourseRepository.deleteAllByCourse_Id(courseId);

        // deleteAll bypasses "if exists" check (+extra request to DB) and prevents from exception raise on deleting a non-existent entity
        courseRepository.deleteAllById(courseId);
        log.info("Deleted course with ID {}", courseId);
    }

    @Override
    public Course createCourse(CourseRequest request) {
        var course = Course.builder()
                .courseName(request.getCourseName())
                .hours(request.getHours())
                .number(request.getNumber())
                .credit(request.getCourseCredit())
                .build();

        courseRepository.save(course);
        log.info("Created course: {}", course);

        return course;
    }

    @Override
    public void updateCourse(Long courseId, CourseRequest request) {
        var updatedCourse = courseRepository.findById(courseId)
                .map(course -> {
                    if (request.getCourseName() != null) {
                        course.setCourseName(request.getCourseName());
                    }

                    if (request.getHours() != null) {
                        course.setHours(request.getHours());
                    }
                    if (request.getNumber() != null) {
                        course.setNumber(request.getNumber());
                    }
                    if (request.getCourseCredit() != null) {
                        course.setCredit(request.getCourseCredit());
                    }

                    return courseRepository.save(course);
                })
                .orElseThrow(() -> {
                    log.error("Update failed. No course found for ID: {}", courseId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No course found with the ID specified");
                });

        log.info("Updated course: {}", updatedCourse);
    }

    @Override
    public CourseStudentResponse getStudentsByCourseId(Long courseId) {
        var students = studentRepository.getAllStudentsByCourseId(courseId);
        return CourseStudentResponse.builder()
                .students(students)
                .courseId(courseId)
                .build();
    }

    @Override
    public Map<String, Long> getCoursesWithNumberOfStudents() {

        return studentCourseRepository.findAll()
                .stream()
                .map(StudentCourse::getCourse)
                .collect(Collectors.groupingBy(
                        Course::getCourseName,
                        Collectors.counting()
                ));
    }


    @Override
    public List<CoursesStudentsAggregationResponse> getStudentsCountByCourses() {
        return studentCourseRepository.getStudentsCountPerCourses();
    }
}
