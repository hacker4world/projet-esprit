package com.example.futurbe.dto.TestEvaluationDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutTestEvaluationRequest {
    long id;
    float note;
    long subjectTestId;

}
