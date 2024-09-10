package com.example.futurbe.dto.subjectTestDTOs;

import com.example.futurbe.entitys.SubjectTestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSubjectTestRequest {
    SubjectTestType testType;
    float coefficient;
    long subjectId;
}
