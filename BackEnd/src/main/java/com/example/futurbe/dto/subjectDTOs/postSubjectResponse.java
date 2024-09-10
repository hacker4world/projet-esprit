package com.example.futurbe.dto.subjectDTOs;

import com.example.futurbe.entitys.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class postSubjectResponse {
    public  postSubjectResponse(Subject subject){
        id = subject.getId();
        totalHours = subject.getTotalHours();
        coefficient = subject.getCoefficient();
        name = subject.getName();
        createdDate = subject.getCreatedDate();
        classRoomId = subject.getClassroom().getId();
        classRoomGroupName = subject.getClassroom().getGroupName();
    }
    long id;
    int totalHours;
    float coefficient;
    String name;
    long classRoomId;
    Date createdDate;
    String classRoomGroupName;
}
