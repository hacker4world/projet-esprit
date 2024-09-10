package com.example.futurbe.dto.subjectDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class postSubjectRequest {
    int totalHours;
    String name;
    float coefficient;
    long classRoomId;
}
