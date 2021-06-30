package com.bambidynamic.random;

import com.bambidynamic.entity.Course;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCourseUtils {

    private static final String [] COURSE_NAMES = {
            "Java",
            "C#",
            "Algorithms",
            "Development Methodologies",
            "C++",
            "RDBMS",
            "NoSQL",
            "MongoDB",
            "Big Data"
    };

    private static final Double CREDIT_MIN = 1.0;
    private static final Double CREDIT_MAX = 10.0;



    private static String getRandomCourseName() {
        int randomCourse = ThreadLocalRandom.current().nextInt(COURSE_NAMES.length);
        return COURSE_NAMES[randomCourse];
    }

    public static Double getRandomCourseCredit() {
        return ThreadLocalRandom.current().nextDouble(CREDIT_MIN, CREDIT_MAX);
    }

    public static Integer getRandomCourseHours() {
        return ThreadLocalRandom.current().nextInt(20, 100);
    }

    public static Integer getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }

    public static Course getRandomCourse() {
        return Course.builder()
                .credit(getRandomCourseCredit())
                .courseName(getRandomCourseName())
                .number(getRandomNumber())
                .hours(getRandomCourseHours())
                .build();
    }

}
