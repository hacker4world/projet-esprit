package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.dto.Documents.getAllDocumentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllReclamationPagedResponse {
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private List<getAllReclamationResponse> reclamations;

    public GetAllReclamationPagedResponse(int totalPages, int pageNumber, int pageSize, List<getAllReclamationResponse> documents) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber +1;
        this.pageSize = pageSize;
        this.reclamations = documents;
    }
}
