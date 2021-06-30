package com.bambidynamic.services;

public interface StudentCourseOperationsService {

    void applyForCourse(Long studentId, Long courseId);
    void removeStudentFromCourse(Long studentId, Long courseId);
}
