package com.example.futurbe.dto.subjectTestDTOs;

import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.entitys.SubjectTestType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllSubjectTestResponse {
    public  GetAllSubjectTestResponse(SubjectTest test){
        id = test.getId();
        testType = test.getTestType();
        coefficient = test.getCoefficient();
        subjectId = test.getSubject().getId();
        subjectName = test.getSubject().getName();
    }

    long id;
    @Enumerated(EnumType.STRING)
    SubjectTestType testType;
    float coefficient;
    long subjectId;
    String subjectName;


}
