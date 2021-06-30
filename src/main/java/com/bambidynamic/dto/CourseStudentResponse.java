package com.bambidynamic.dto;

import com.bambidynamic.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseStudentResponse {

    private Long courseId;
    private List<Student> students;

}
