package com.example.futurbe.controller;

import com.example.futurbe.dto.TestEvaluationDTOs.*;
import com.example.futurbe.services.iservices.itestEvalutionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/TestEvalution")
public class TestEvalutionController {
    itestEvalutionService testEvalutionService;

    @GetMapping
    public GetTestEvaluationPagedResponse getAll(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return testEvalutionService.getAll(pageNumber-1, pageSize);
    }

    @GetMapping("/subject-test/{subjectTestId}")
    public List<GetTestEvaluationResponse> getAllBySubjectTest(
            @PathVariable Long subjectTestId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return testEvalutionService.getAllBySubjectTest(subjectTestId, pageNumber, pageSize);
    }
    @GetMapping(value = "/AllByUser/{userId}")
    public getAllTestEvalutionsByUserResponse getAllByUser(@PathVariable Long userId) {
        getAllTestEvalutionsByUserResponse result = testEvalutionService.getAllByUser(userId);
        return result;
    }

    @GetMapping("/missing/{userId}")
    public List<GetTestEvaluationDetailedResponse> getMissingEvaluationsByUser(@PathVariable Long userId) {
        return testEvalutionService.getMissingEvaluationsByUser(userId);
    }

    @GetMapping("/calculateAvgGrade/{userId}")
    public getTestEvalutionResultResponse calculateAvgGrade(@PathVariable Long userId){
        return testEvalutionService.calculateAvgGrade(userId);
    }
    @PostMapping
    public PostTestEvaluationResponse createTestEvaluation(@RequestBody PostTestEvaluationRequest request) {
        return testEvalutionService.post(request);
    }
    @PutMapping
    public PutTestEvaluationResponse updateTestEvaluation(@RequestBody PutTestEvaluationRequest request) {
        return testEvalutionService.put(request);
    }


}
