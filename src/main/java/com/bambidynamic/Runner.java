package com.bambidynamic;


import com.bambidynamic.entity.Lecturer;
import com.bambidynamic.random.RandomCourseUtils;
import com.bambidynamic.random.RandomLectorUtils;
import com.bambidynamic.random.RandomStudentUtils;
import com.bambidynamic.repositories.CourseRepository;
import com.bambidynamic.repositories.LecturerRepository;
import com.bambidynamic.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;


    @Override
    @Transactional
    public void run(String... args) {

        // saveAll runs internal for loop, saving one-by-one, so no performance savings out of saveAll

        Stream
                .generate(RandomCourseUtils::getRandomCourse)
                .limit(10)
                .forEach(courseRepository::save);

        Stream
                .generate(RandomStudentUtils::getRandomStudent)
                .limit(10)
                .forEach(studentRepository::save);

        Stream
                .generate(RandomLectorUtils::getRandomLecturer)
                .limit(10)
                .forEach(lecturerRepository::save);

    }


}


