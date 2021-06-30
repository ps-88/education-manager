package com.bambidynamic.random;

import com.bambidynamic.entity.Student;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStudentUtils {

    private static final String[] STUDENT_NAMES = {
            "Anna",
            "Marry",
            "Jack",
            "David",
            "Peter",
            "Simon",
            "Sandy"
    };

    private static final String[] STUDENT_LAST_NAMES = {
            "Dale",
            "Thomas",
            "Smith",
            "Stone",
            "Stale",
            "Winter"
    };

    private static final String[] FACULTIES = {
            "Computer Science",
            "Psychology",
            "Analytics",
    };

    private static final String[] CITIES = {
            "City 1",
            "City 2",
            "City 3",
            "City 4",
            "City 5",
    };

    private static final String[] STREETS = {
            "Street 1",
            "Street 2",
            "Street 3",
            "Street 4",
            "Street 5",
    };

    public static String getRandomFirstName() {
        int random = ThreadLocalRandom.current().nextInt(STUDENT_NAMES.length);
        return STUDENT_NAMES[random];
    }

    public static String getRandomLastName() {
        int random = ThreadLocalRandom.current().nextInt(STUDENT_LAST_NAMES.length);
        return STUDENT_LAST_NAMES[random];
    }

    public static String getRandomCity() {
        int random = ThreadLocalRandom.current().nextInt(CITIES.length);
        return CITIES[random];
    }

    public static String getRandomStreet() {
        int random = ThreadLocalRandom.current().nextInt(STREETS.length);
        return STREETS[random];
    }

    public static String getRandomFaculty() {
        int random = ThreadLocalRandom.current().nextInt(FACULTIES.length);
        return FACULTIES[random];
    }

    public static LocalDate getRandomDateOfBirth() {
        long minDay = LocalDate.of(1950, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2005, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Student getRandomStudent() {
        return Student.builder()
                .dateOfBirth(getRandomDateOfBirth())
                .street(getRandomStreet())
                .city(getRandomCity())
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .faculty(getRandomFaculty())
                .build();
    }
}
