package com.example.futurbe.dto.subjectDTOs;

import com.example.futurbe.entitys.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PutSubjectResponse {
    public  PutSubjectResponse(Subject subject){
        id = subject.getId();
        totalHours = subject.getTotalHours();
        name = subject.getName();
        coefficient = subject.getCoefficient();
        updatedDate = subject.getUpdatedDate();
        classRoomId = subject.getClassroom().getId();
        classRoomGroupName = subject.getClassroom().getGroupName();
    }
    long id;
    int totalHours;
    float coefficient;
    long classRoomId;
    String name;
    Date updatedDate;
    String classRoomGroupName;
}
