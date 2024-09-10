package com.example.futurbe.dto.subjectTestDTOs;

import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.entitys.SubjectTestType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PutSubjectTestResponse {
    public  PutSubjectTestResponse(SubjectTest test){
        id = test.getId();
        testType = test.getTestType();
        coefficient = test.getCoefficient();
        subjectId = test.getSubject().getId();
        subjectName = test.getSubject().getName();
        updatedDate = test.getUpdatedDate();
    }

    long id;
    @Enumerated(EnumType.STRING)
    SubjectTestType testType;
    float coefficient;
    long subjectId;
    String subjectName;
    Date updatedDate;
}
