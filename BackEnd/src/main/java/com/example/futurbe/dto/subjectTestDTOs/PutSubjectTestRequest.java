package com.example.futurbe.dto.subjectTestDTOs;

import com.example.futurbe.entitys.SubjectTestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutSubjectTestRequest {
    long id;
    float coefficient;
}
