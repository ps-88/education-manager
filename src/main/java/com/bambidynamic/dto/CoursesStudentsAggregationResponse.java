package com.bambidynamic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesStudentsAggregationResponse {
    private Long courseId;
    private String courseName;
    private Long studentsCount;
}
