package com.bambidynamic.services;

import com.bambidynamic.dto.LecturerRequest;
import com.bambidynamic.entity.Lecturer;

import java.util.List;

public interface LecturerService {


    List<Lecturer> findAll();

    Lecturer findById(Long id);
    Lecturer createLecturer(LecturerRequest request);

    void updateLecturer(Long lecturerId, LecturerRequest request);
    void deleteById(Long id);
}
