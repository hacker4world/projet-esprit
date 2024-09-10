package com.example.futurbe.dto.subjectTestDTOs;

import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.entitys.SubjectTestType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class getSubjectTestByIdResponse {
    public  getSubjectTestByIdResponse(SubjectTest test){
        id = test.getId();
        testType = test.getTestType();
        coefficient = test.getCoefficient();
        subjectId = test.getSubject().getId();
        subjectName = test.getSubject().getName();
        createdDate = test.getCreatedDate();
        updatedDate = test.getUpdatedDate();
    }

    long id;
    @Enumerated(EnumType.STRING)
    SubjectTestType testType;
    float coefficient;
    long subjectId;
    String subjectName;
    Date createdDate;
    Date updatedDate;
}
