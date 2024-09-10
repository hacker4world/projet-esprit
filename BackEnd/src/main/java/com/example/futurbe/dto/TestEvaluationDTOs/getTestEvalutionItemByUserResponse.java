package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.Subject;
import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.entitys.SubjectTestType;
import com.example.futurbe.entitys.TestEvalution;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getTestEvalutionItemByUserResponse {
    public getTestEvalutionItemByUserResponse(TestEvalution testEvalution, Subject subject, SubjectTest subjectTest){
        subjectId = subject.getId();
        subjectName = subject.getName();
        subjectTestId = subjectTest.getId();
        subjectTestType = subjectTest.getTestType();
        testEvalutionId = testEvalution.getId();
        note = testEvalution.getNote();
        userId = testEvalution.getUser().getId();
    }
    long subjectId;
    String subjectName;
    long subjectTestId;
    @Enumerated(EnumType.STRING)
    SubjectTestType subjectTestType;
    long testEvalutionId;
    float note;
    long userId;
}
