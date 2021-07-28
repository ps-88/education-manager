package com.bambidynamic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequest {

    private Long idNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String city;
    private String street;
    private String faculty;
}
