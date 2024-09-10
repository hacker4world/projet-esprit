package com.example.futurbe.services;

import com.example.futurbe.dto.subjectDTOs.getSubjectByIdResponse;
import com.example.futurbe.dto.subjectTestDTOs.*;
import com.example.futurbe.entitys.Subject;
import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.repositorys.SubjectRepository;
import com.example.futurbe.repositorys.SubjectTestRepository;
import com.example.futurbe.services.iservices.isubjectTestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class subjectTestService implements isubjectTestService {
    SubjectRepository subjectRepository;
    SubjectTestRepository subjectTestRepository;
    public GetAllSubjectTestPagedResponse getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<SubjectTest> subjectTestPage = subjectTestRepository.findAll(pageRequest);

        List<GetAllSubjectTestResponse> subjectTests = subjectTestPage.getContent().stream()
                .map(GetAllSubjectTestResponse::new)
                .collect(Collectors.toList());

        return new GetAllSubjectTestPagedResponse(
                subjectTests,
                subjectTestPage.getTotalPages(),
                pageNumber,
                pageSize
        );
    }

    public List<GetAllSubjectTestResponse> getAllByClassroom(Long classroomId) {
        List<SubjectTest> subjectTests = subjectTestRepository.findAllByClassroomId(classroomId);
        return subjectTests.stream()
                .map(GetAllSubjectTestResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetAllSubjectTestResponse> getAllBySubject(Long subjectId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<SubjectTest> subjectTestPage = subjectTestRepository.findBySubjectId(subjectId, pageRequest);

        return subjectTestPage.getContent().stream()
                .map(GetAllSubjectTestResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public getSubjectTestByIdResponse getById(Long id) {
        SubjectTest subjectTest = subjectTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubjectTest not found"));

        return new getSubjectTestByIdResponse(subjectTest);
    }

    @Override
    public PostSubjectTestResponse post(PostSubjectTestRequest request) {
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        SubjectTest subjectTest = new SubjectTest();
        subjectTest.setTestType(request.getTestType());
        subjectTest.setCoefficient(request.getCoefficient());
        subjectTest.setSubject(subject);
        subjectTest.setCreatedDate(new Date());

        SubjectTest savedSubjectTest = subjectTestRepository.save(subjectTest);

        return new PostSubjectTestResponse(savedSubjectTest);
    }

    @Override
    public PutSubjectTestResponse put(PutSubjectTestRequest request) {
        SubjectTest subjectTest = subjectTestRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("SubjectTest not found"));

        subjectTest.setCoefficient(request.getCoefficient());
        subjectTest.setUpdatedDate(new Date());

        SubjectTest updatedSubjectTest = subjectTestRepository.save(subjectTest);

        return new PutSubjectTestResponse(updatedSubjectTest);
    }
}
