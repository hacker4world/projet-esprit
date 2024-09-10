package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.Subject;
import com.example.futurbe.entitys.SubjectTestType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getTestEvalutionGradeAvgItemResponse {
    public getTestEvalutionGradeAvgItemResponse(long avgGradePerSubject, Subject subject) {
        this.avgGradePerSubject = avgGradePerSubject;
        this.subjectId = subject.getId();
        subjectName = subject.getName();
    }

    long avgGradePerSubject;
    long subjectId;
    String subjectName;
}
