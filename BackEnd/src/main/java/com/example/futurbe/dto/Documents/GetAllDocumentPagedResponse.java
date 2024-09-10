package com.example.futurbe.dto.Documents;

import com.example.futurbe.dto.classRoomDTOs.getAllClassRoomResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllDocumentPagedResponse {
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private List<getAllDocumentResponse> documents;

    public GetAllDocumentPagedResponse(int totalPages, int pageNumber, int pageSize, List<getAllDocumentResponse> documents) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber +1;
        this.pageSize = pageSize;
        this.documents = documents;
    }
}
