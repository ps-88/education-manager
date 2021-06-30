package com.bambidynamic.controller;

import com.bambidynamic.dto.LecturerRequest;
import com.bambidynamic.entity.Lecturer;
import com.bambidynamic.services.LecturerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LecturerController {

    private final LecturerService lecturerService;

    @GetMapping("/lecturers")
    public List<Lecturer> findAll(){
        return lecturerService.findAll();
    }

    @GetMapping("/lecturers/{id}")
    public Lecturer findById(@PathVariable("id") Long id){
        return lecturerService.findById(id);
    }


    @PostMapping("/lecturers")
    public Lecturer createLecturer(@RequestBody LecturerRequest request){
        return  lecturerService.createLecturer(request);

    }

    @DeleteMapping("lecturers/{id}")
    public void deleteLecturer(@PathVariable("id") Long id){
        lecturerService.deleteById(id);
    }

    @PutMapping("/lecturers/{id}")
    public void updateLecturer(@PathVariable("id") Long lecturerId, @RequestBody LecturerRequest request){
        lecturerService.updateLecturer(lecturerId, request);

    }


}
