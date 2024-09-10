package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.TestEvalution;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostTestEvaluationResponse {
    public  PostTestEvaluationResponse(TestEvalution test){
        id = test.getId();
        note = test.getNote();
        createdDate = test.getCreatedDate();
        subjectTestId = test.getSubjectTest().getId();
        userId = test.getUser().getId();
    }
    long id;
    float note;
    Date createdDate;
    long subjectTestId;
    long userId;

}
