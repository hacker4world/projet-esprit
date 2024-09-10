package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.subjectDTOs.getSubjectByIdResponse;
import com.example.futurbe.dto.subjectTestDTOs.*;

import java.util.List;

public interface isubjectTestService {
    GetAllSubjectTestPagedResponse getAll(int pageNumber, int pageSize);
    List<GetAllSubjectTestResponse> getAllByClassroom(Long classroomId);
    getSubjectTestByIdResponse getById(Long id);
    List<GetAllSubjectTestResponse> getAllBySubject(Long subjectId, int pageNumber, int pageSize);
    PostSubjectTestResponse post(PostSubjectTestRequest request);
    PutSubjectTestResponse put(PutSubjectTestRequest request);
}
