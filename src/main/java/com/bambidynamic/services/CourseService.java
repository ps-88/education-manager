package com.bambidynamic.services;

import com.bambidynamic.dto.CourseRequest;
import com.bambidynamic.dto.CourseStudentResponse;
import com.bambidynamic.dto.CoursesStudentsAggregationResponse;
import com.bambidynamic.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    List<Course> findAll();

    Course findById(Long id);

    Course createCourse(CourseRequest request);

    void updateCourse(Long courseId, CourseRequest request);

    void deleteById(Long id);

    CourseStudentResponse getStudentsByCourseId(Long courseId);

    Map<String, Long> getCoursesWithNumberOfStudents();

    List<CoursesStudentsAggregationResponse> getStudentsCountByCourses();

}
