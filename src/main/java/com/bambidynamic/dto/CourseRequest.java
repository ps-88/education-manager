package com.bambidynamic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRequest {

    private String courseName;
    private Double courseCredit;
    private Integer hours;
    private Integer number;
}
