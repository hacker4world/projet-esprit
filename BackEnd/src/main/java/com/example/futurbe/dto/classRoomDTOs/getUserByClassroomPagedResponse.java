package com.example.futurbe.dto.classRoomDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class getUserByClassroomPagedResponse {

    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private List<getUserByClassroomResponse> users;

    public getUserByClassroomPagedResponse(int totalPages, int pageNumber, int pageSize, List<getUserByClassroomResponse> users) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber +1;
        this.pageSize = pageSize;
        this.users = users;
    }
}
