package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.TestEvalution;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PutTestEvaluationResponse {
    public  PutTestEvaluationResponse(TestEvalution test){
        id = test.getId();
        note = test.getNote();
        updatedDate = test.getUpdatedDate();
        subjectTestId = test.getSubjectTest().getId();

    }
    long id;
    float note;
    Date updatedDate;
    long subjectTestId;

}
