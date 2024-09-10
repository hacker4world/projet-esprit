package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.subjectDTOs.*;

import java.util.List;

public interface isubjectService {
    GetAllSubjectPagedResponse getAll(int pageNumber, int pageSize);
    GetAllSubjectPagedResponse getAllByClassroom(Long classroomId, int pageNumber, int pageSize);
    getSubjectByIdResponse getSubjectById(long subjectId);
    postSubjectResponse post(postSubjectRequest request);
    PutSubjectResponse put(PutSubjectRequest request);
}
