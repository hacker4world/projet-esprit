package com.example.futurbe.controller;

import com.example.futurbe.dto.subjectTestDTOs.*;
import com.example.futurbe.services.iservices.isubjectTestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/SubjectTest")
public class SubjectTestController {
    isubjectTestService subjectTestService;

    @GetMapping
    public GetAllSubjectTestPagedResponse getAllSubjectTests(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return subjectTestService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/classroom/{classroomId}")
    public List<GetAllSubjectTestResponse> getAllByClassroom(@PathVariable Long classroomId) {
        return subjectTestService.getAllByClassroom(classroomId);
    }

    @GetMapping("/subject/{subjectId}")
    public List<GetAllSubjectTestResponse> getAllSubjectTestsBySubject(
            @PathVariable Long subjectId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return subjectTestService.getAllBySubject(subjectId, pageNumber, pageSize);
    }
    @GetMapping("{id}")
    public getSubjectTestByIdResponse getById(
            @PathVariable Long id) {
        return subjectTestService.getById(id);
    }
    @PostMapping
    public PostSubjectTestResponse createSubjectTest(@RequestBody PostSubjectTestRequest request) {
        return subjectTestService.post(request);
    }

    @PutMapping
    public PutSubjectTestResponse updateSubjectTest(@RequestBody PutSubjectTestRequest request) {
        return subjectTestService.put(request);
    }
}
