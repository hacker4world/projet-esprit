package com.example.futurbe.dto.subjectDTOs;

import com.example.futurbe.dto.subjectTestDTOs.GetAllSubjectTestResponse;
import com.example.futurbe.entitys.Subject;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class getSubjectByIdResponse {
    public  getSubjectByIdResponse(Subject subject, List<GetAllSubjectTestResponse> subjectTests) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.totalHours = subject.getTotalHours();
        this.coefficient = subject.getCoefficient();
        this.classRoomId = subject.getClassroom().getId();
        this.classRoomGroupName = subject.getClassroom().getGroupName();
        this.createdDate = subject.getCreatedDate();
        this.updatedDate = subject.getUpdatedDate();
        this.subjectTests = subjectTests;
    }
    long id;
    String name;
    int totalHours;
    float coefficient;
    long classRoomId;
    String classRoomGroupName;
    Date createdDate;
    Date updatedDate;

    List<GetAllSubjectTestResponse> subjectTests;
}
