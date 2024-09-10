package com.example.futurbe.dto.subjectDTOs;

import com.example.futurbe.entitys.Subject;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class getAllSubjectResponse {
    public  getAllSubjectResponse(Subject subject){
        id = subject.getId();
        totalHours = subject.getTotalHours();
        name = subject.getName();
        coefficient = subject.getCoefficient();
        classRoomId = subject.getClassroom().getId();
        classRoomGroupName = subject.getClassroom().getGroupName();
    }

    long id;
    String name;
    int totalHours;
    float coefficient;
    long classRoomId;
    String classRoomGroupName;
}
