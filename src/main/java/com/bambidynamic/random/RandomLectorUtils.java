package com.bambidynamic.random;

import com.bambidynamic.entity.Lecturer;

public class RandomLectorUtils {

    public static Lecturer getRandomLecturer() {


        return Lecturer.builder()
                .street(RandomStudentUtils.getRandomStreet())
                .city(RandomStudentUtils.getRandomCity())
                .firstName(RandomStudentUtils.getRandomFirstName())
                .lastName(RandomStudentUtils.getRandomLastName())
                .build();
    }
}
