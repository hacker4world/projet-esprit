package com.example.futurbe.dto.subjectTestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllSubjectTestPagedResponse {
    private List<GetAllSubjectTestResponse> subjectTests;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    // Constructor
    public GetAllSubjectTestPagedResponse(List<GetAllSubjectTestResponse> subjectTests, int totalPages, int pageNumber, int pageSize) {
        this.subjectTests = subjectTests;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
