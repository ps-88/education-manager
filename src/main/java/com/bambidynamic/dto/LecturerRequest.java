package com.bambidynamic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LecturerRequest {

    private String firstName;
    private String lastName;
    private String city;
    private String street;

}