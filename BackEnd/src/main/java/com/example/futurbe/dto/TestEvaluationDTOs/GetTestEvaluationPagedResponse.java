package com.example.futurbe.dto.TestEvaluationDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTestEvaluationPagedResponse {
    private List<GetTestEvaluationResponse> evaluations;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    // Constructor
    public GetTestEvaluationPagedResponse(List<GetTestEvaluationResponse> evaluations, int totalPages, int pageNumber, int pageSize) {
        this.evaluations = evaluations;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
