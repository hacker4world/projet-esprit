package com.example.futurbe.dto.TestEvaluationDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTestEvaluationRequest {
    float note;
    long subjectTestId;
    long UserId;
}
