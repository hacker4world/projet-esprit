package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.TestEvaluationDTOs.*;

import java.util.List;

public interface itestEvalutionService {
    GetTestEvaluationPagedResponse getAll(int pageNumber, int pageSize);
    List<GetTestEvaluationResponse> getAllBySubjectTest(Long subjectTestId, int pageNumber, int pageSize);
    List<GetTestEvaluationDetailedResponse> getMissingEvaluationsByUser(Long userId);
    PostTestEvaluationResponse post(PostTestEvaluationRequest request);
    PutTestEvaluationResponse put(PutTestEvaluationRequest request);
    getAllTestEvalutionsByUserResponse getAllByUser(Long userId);
    getTestEvalutionResultResponse calculateAvgGrade(Long userId);
}
