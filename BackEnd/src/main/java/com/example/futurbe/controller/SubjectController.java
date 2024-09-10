package com.example.futurbe.controller;

import com.example.futurbe.dto.subjectDTOs.*;
import com.example.futurbe.services.iservices.isubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Subject")
public class SubjectController {
    isubjectService subjectService;

    @GetMapping
    public GetAllSubjectPagedResponse getAllSubjects(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return subjectService.getAll(pageNumber -1, pageSize);
    }

    @GetMapping("/ByClassroom/{classroomId}")
    public GetAllSubjectPagedResponse getAllSubjectsByClassroom(
            @PathVariable Long classroomId,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return subjectService.getAllByClassroom(classroomId, pageNumber -1, pageSize);
    }

    @GetMapping("/{id}")
    public getSubjectByIdResponse getSubjectById(
            @PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public postSubjectResponse createSubject(@RequestBody postSubjectRequest request) {
        return subjectService.post(request);
    }

    @PutMapping
    public PutSubjectResponse updateSubject(@RequestBody PutSubjectRequest request) {
        return subjectService.put(request);
    }
}
