package com.example.futurbe.dto.classRoomDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllClassRoomPagedResponse {
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private List<getAllClassRoomResponse> classrooms;

    public GetAllClassRoomPagedResponse(int totalPages, int pageNumber, int pageSize, List<getAllClassRoomResponse> classrooms) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber +1;
        this.pageSize = pageSize;
        this.classrooms = classrooms;
    }
}
