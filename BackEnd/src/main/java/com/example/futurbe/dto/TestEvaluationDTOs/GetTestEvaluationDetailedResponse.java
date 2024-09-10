package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.SubjectTestType;
import com.example.futurbe.entitys.TestEvalution;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTestEvaluationDetailedResponse {
    public GetTestEvaluationDetailedResponse(){

    }

    long subjectTestId;
    @Enumerated(EnumType.STRING)
    SubjectTestType testType;
    long subjectId;
    String SubjectName;
}
