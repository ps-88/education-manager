package com.bambidynamic.dto;

import com.bambidynamic.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentCourseResponse {
    private Long studentId;
    private List<Course> courses;
}
