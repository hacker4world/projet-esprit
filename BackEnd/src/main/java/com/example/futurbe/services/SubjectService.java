package com.example.futurbe.services;

import com.example.futurbe.dto.subjectDTOs.*;
import com.example.futurbe.dto.subjectTestDTOs.GetAllSubjectTestResponse;
import com.example.futurbe.entitys.ClassRoom;
import com.example.futurbe.entitys.Subject;
import com.example.futurbe.repositorys.ClassroomRepository;
import com.example.futurbe.repositorys.SubjectRepository;
import com.example.futurbe.services.iservices.isubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService implements isubjectService {

    SubjectRepository subjectRepository;
    ClassroomRepository classroomRepository;

    @Override
    public GetAllSubjectPagedResponse getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Subject> subjectPage = subjectRepository.findAll(pageRequest);

        List<getAllSubjectResponse> subjects = subjectPage.getContent().stream()
                .map(getAllSubjectResponse::new)
                .collect(Collectors.toList());

        return new GetAllSubjectPagedResponse(subjectPage.getTotalPages(), pageNumber, pageSize, subjects);
    }

    @Override
    public GetAllSubjectPagedResponse getAllByClassroom(Long classroomId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Subject> subjectPage = subjectRepository.findByClassroomId(classroomId, pageRequest);

        List<getAllSubjectResponse> subjects = subjectPage.getContent().stream()
                .map(getAllSubjectResponse::new)
                .collect(Collectors.toList());

        return new GetAllSubjectPagedResponse(subjectPage.getTotalPages(), pageNumber, pageSize, subjects);
    }

    @Override
    public getSubjectByIdResponse getSubjectById(long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        List<GetAllSubjectTestResponse> subjectTestResponses = subject.getSubjectTests().stream()
                .map(GetAllSubjectTestResponse::new)
                .collect(Collectors.toList());

        return new getSubjectByIdResponse(subject, subjectTestResponses);
    }

    @Override
    public postSubjectResponse post(postSubjectRequest request) {
        ClassRoom classroom = classroomRepository.findById(request.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        Subject subject = new Subject();
        subject.setTotalHours(request.getTotalHours());
        subject.setName((request.getName()));
        subject.setCoefficient(request.getCoefficient());
        subject.setClassroom(classroom);
        subject.setCreatedDate(new Date());

        Subject savedSubject = subjectRepository.save(subject);

        return new postSubjectResponse(savedSubject);
    }

    @Override
    public PutSubjectResponse put(PutSubjectRequest request) {
        Subject subject = subjectRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        ClassRoom classroom = classroomRepository.findById(request.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        subject.setTotalHours(request.getTotalHours());
        subject.setCoefficient(request.getCoefficient());
        subject.setClassroom(classroom);
        subject.setUpdatedDate(new Date());

        Subject updatedSubject = subjectRepository.save(subject);

        return new PutSubjectResponse(updatedSubject);
    }
}
