package com.example.futurbe.services;

import com.example.futurbe.dto.TestEvaluationDTOs.*;
import com.example.futurbe.entitys.Subject;
import com.example.futurbe.entitys.SubjectTest;
import com.example.futurbe.entitys.TestEvalution;
import com.example.futurbe.entitys.User;
import com.example.futurbe.repositorys.SubjectTestRepository;
import com.example.futurbe.repositorys.TestEvaluationRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.itestEvalutionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestEvalutionService implements itestEvalutionService {
    TestEvaluationRepository testEvalutionRepository;
    SubjectTestRepository subjectTestRepository;
    UserRepository userRepository;

    @Override
    public GetTestEvaluationPagedResponse getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<TestEvalution> testEvalutionPage = testEvalutionRepository.findAll(pageRequest);

        List<GetTestEvaluationResponse> evaluations = testEvalutionPage.getContent().stream()
                .map(GetTestEvaluationResponse::new)
                .collect(Collectors.toList());

        return new GetTestEvaluationPagedResponse(
                evaluations,
                testEvalutionPage.getTotalPages(),
                pageNumber,
                pageSize
        );
    }

    public List<GetTestEvaluationDetailedResponse> getMissingEvaluationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long classroomId = user.getClassroom().getId();
        List<SubjectTest> allSubjectTests = subjectTestRepository.findAllByClassroomId(classroomId);
        List<TestEvalution> userEvaluations = testEvalutionRepository.findByUser(user);

        Set<Long> evaluatedSubjectTestIds = userEvaluations.stream()
                .map(te -> te.getSubjectTest().getId())
                .collect(Collectors.toSet());

        List<GetTestEvaluationDetailedResponse> missingEvaluations = allSubjectTests.stream()
                .filter(st -> !evaluatedSubjectTestIds.contains(st.getId()))
                .map(st -> {
                    GetTestEvaluationDetailedResponse response = new GetTestEvaluationDetailedResponse();
                    response.setSubjectTestId(st.getId());
                    response.setTestType(st.getTestType());
                    response.setSubjectId(st.getSubject().getId());
                    response.setSubjectName(st.getSubject().getName());
                    return response;
                })
                .collect(Collectors.toList());

        return missingEvaluations;
    }

    @Override
    public List<GetTestEvaluationResponse> getAllBySubjectTest(Long subjectTestId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<TestEvalution> testEvalutionPage = testEvalutionRepository.findBySubjectTestId(subjectTestId, pageRequest);

        return testEvalutionPage.getContent().stream()
                .map(GetTestEvaluationResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostTestEvaluationResponse post(PostTestEvaluationRequest request) {
        SubjectTest subjectTest = subjectTestRepository.findById(request.getSubjectTestId())
                .orElseThrow(() -> new RuntimeException("SubjectTest not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TestEvalution testEvalution = new TestEvalution();
        testEvalution.setNote(request.getNote());
        testEvalution.setSubjectTest(subjectTest);
        testEvalution.setCreatedDate(new Date());
        testEvalution.setUser(user);

        TestEvalution savedTestEvalution = testEvalutionRepository.save(testEvalution);

        return new PostTestEvaluationResponse(savedTestEvalution);
    }

    @Override
    public PutTestEvaluationResponse put(PutTestEvaluationRequest request) {
        TestEvalution testEvalution = testEvalutionRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("TestEvalution not found"));

        SubjectTest subjectTest = subjectTestRepository.findById(request.getSubjectTestId())
                .orElseThrow(() -> new RuntimeException("SubjectTest not found"));

        testEvalution.setNote(request.getNote());
        testEvalution.setSubjectTest(subjectTest);
        testEvalution.setUpdatedDate(new Date());

        TestEvalution updatedTestEvalution = testEvalutionRepository.save(testEvalution);

        return new PutTestEvaluationResponse(updatedTestEvalution);
    }

    @Override
    public getAllTestEvalutionsByUserResponse getAllByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<TestEvalution> testEvalutions = testEvalutionRepository.findByUser(user);

        List<getTestEvalutionItemByUserResponse> testEvalutionDTOs = testEvalutions.stream().map(te -> {
            Subject subject = te.getSubjectTest().getSubject();
            SubjectTest subjectTest = te.getSubjectTest();
            return new getTestEvalutionItemByUserResponse(te, subject, subjectTest);
        }).collect(Collectors.toList());

        return new getAllTestEvalutionsByUserResponse(user, testEvalutionDTOs);
    }

    @Override
    public getTestEvalutionResultResponse calculateAvgGrade(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TestEvalution> testEvalutions = testEvalutionRepository.findByUser(user);

        Map<Subject, List<TestEvalution>> evaluationsBySubject = testEvalutions.stream()
                .collect(Collectors.groupingBy(te -> te.getSubjectTest().getSubject()));

        List<getTestEvalutionGradeAvgItemResponse> testEvalutionsAvgGrades = new ArrayList<>();

        float totalWeightedSubjectGrades = 0;
        float totalSubjectCoefficients = 0;

        for (Map.Entry<Subject, List<TestEvalution>> entry : evaluationsBySubject.entrySet()) {
            Subject subject = entry.getKey();
            List<TestEvalution> evaluations = entry.getValue();

            float totalWeightedGrades = 0;
            float totalCoefficients = 0;

            for (TestEvalution eval : evaluations) {
                float coefficient = eval.getSubjectTest().getCoefficient();
                totalWeightedGrades += eval.getNote() * coefficient;
                totalCoefficients += coefficient;
            }

            float avgGradePerSubject = totalWeightedGrades / totalCoefficients;
            totalWeightedSubjectGrades += avgGradePerSubject * subject.getCoefficient();
            totalSubjectCoefficients += subject.getCoefficient();

            testEvalutionsAvgGrades.add(new getTestEvalutionGradeAvgItemResponse(
                    Math.round(avgGradePerSubject),
                    subject
            ));
        }

        long generalAvgGrade = Math.round(totalWeightedSubjectGrades / totalSubjectCoefficients);
        TestEvalutionStatus status = generalAvgGrade >= 10 ? TestEvalutionStatus.Admis : TestEvalutionStatus.Refused;

        return new getTestEvalutionResultResponse(
                user,
                generalAvgGrade,
                status,
                testEvalutionsAvgGrades
        );
    }
}
