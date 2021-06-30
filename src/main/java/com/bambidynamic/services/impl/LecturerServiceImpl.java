package com.bambidynamic.services.impl;

import com.bambidynamic.dto.LecturerRequest;
import com.bambidynamic.entity.Lecturer;
import com.bambidynamic.repositories.LecturerRepository;
import com.bambidynamic.services.LecturerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Override
    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer findById(Long lecturerId) {
        return lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> {
                    log.error("No lecturer found for ID: {}", lecturerId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No lecturer found with the ID specified");
                });
    }

    @Override
    public void deleteById(Long lecturerId) {
        lecturerRepository.deleteById(lecturerId);
        log.info("Deleted lecturer with ID {}", lecturerId);
    }

    @Override
    public Lecturer createLecturer(LecturerRequest request) {
        var lecturer = Lecturer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .city(request.getCity())
                .street(request.getStreet())
                .build();

        lecturerRepository.save(lecturer);
        log.info("Created lecturer: {}", lecturer);

        return lecturer;
    }

    @Override
    public void updateLecturer(Long lecturerId, LecturerRequest request) {
        var updateLecturer = lecturerRepository.findById(lecturerId)
                .map(lecturer -> {
                    if (request.getFirstName() != null) {
                        lecturer.setFirstName(request.getFirstName());
                    }

                    if (request.getLastName() != null) {
                        lecturer.setLastName(request.getLastName());
                    }

                    if (request.getCity() != null) {
                        lecturer.setCity(request.getCity());
                    }

                    if (request.getStreet() != null) {
                        lecturer.setStreet(request.getStreet());
                    }


                    return lecturerRepository.save(lecturer);
                })
                .orElseThrow(() -> {
                    log.error("Update failed. No lecturer found for ID: {}", lecturerId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "No lecturer found with the ID specified");
                });
        log.info("Updated lecturer: {}", updateLecturer);
    }
}
