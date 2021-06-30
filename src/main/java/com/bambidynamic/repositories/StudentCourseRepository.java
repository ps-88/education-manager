package com.bambidynamic.repositories;

import com.bambidynamic.dto.CoursesStudentsAggregationResponse;
import com.bambidynamic.entity.Course;
import com.bambidynamic.entity.Student;
import com.bambidynamic.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    StudentCourse findByCourseAndStudent(Course course, Student student);
    void deleteByCourse_IdAndStudent_Id(Long courseId, Long studentId);
    void deleteAllByCourse_Id(Long courseId);
    void deleteAllByStudent_Id(Long studentId);

    @Query("select new com.bambidynamic.dto.CoursesStudentsAggregationResponse(" +
                "sc.course.id, " +
                "sc.course.courseName, " +
                "count(sc.student)" +
            ") from StudentCourse sc group by sc.course.id")
    List<CoursesStudentsAggregationResponse> getStudentsCountPerCourses();

}
