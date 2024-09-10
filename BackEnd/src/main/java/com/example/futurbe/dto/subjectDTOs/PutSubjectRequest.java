package com.example.futurbe.dto.subjectDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutSubjectRequest {
    long id;
    int totalHours;
    String name;
    float coefficient;
    long classRoomId;
}
