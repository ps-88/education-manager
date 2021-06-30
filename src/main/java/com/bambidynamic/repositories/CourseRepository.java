package com.bambidynamic.repositories;

import com.bambidynamic.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select sc.course from StudentCourse sc where sc.student.id = ?1")
    List<Course> getAllCoursesByStudentId(Long studentId);

    // deleteAll bypasses "if exists" check (+extra request to DB) and prevents from exception raise on deleting a non-existent entity
    void deleteAllById(Long courseId);
}
