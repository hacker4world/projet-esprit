package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.TestEvalution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTestEvaluationResponse {
    public  GetTestEvaluationResponse(TestEvalution test){
        id = test.getId();
        note = test.getNote();
        subjectTestId = test.getSubjectTest().getId(); 
    }
    long id;
    float note;
    long subjectTestId;
}
