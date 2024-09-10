package com.example.futurbe.dto.subjectDTOs;

import com.example.futurbe.dto.classRoomDTOs.getAllClassRoomResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllSubjectPagedResponse {

    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private List<getAllSubjectResponse> subjects;
    public GetAllSubjectPagedResponse(int totalPages, int pageNumber, int pageSize, List<getAllSubjectResponse> subjects) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber +1;
        this.pageSize = pageSize;
        this.subjects = subjects;
    }
}
