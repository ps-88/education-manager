package com.bambidynamic.repositories;

import com.bambidynamic.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select sc.student from StudentCourse sc where sc.course.id = ?1")
    List<Student> getAllStudentsByCourseId(Long courseId);

    // deleteAll bypasses "if exists" check (+extra request to DB) and prevents from exception raise on deleting a non-existent entity
    void deleteAllById(Long studentId);
}
