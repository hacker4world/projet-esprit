package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.ClassRoomLevel;
import com.example.futurbe.entitys.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class getTestEvalutionResultResponse {
    public  getTestEvalutionResultResponse(User student, long generalAvgGrade, TestEvalutionStatus status, List<getTestEvalutionGradeAvgItemResponse> testEvalutionsAvgGrades) {
        this.generalAvgGrade = generalAvgGrade;
        this.status = status;
        this.testEvalutionsAvgGrades = testEvalutionsAvgGrades;
        this.studentId = student.getId();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.studentEmail = student.getEmail();
        this.studentClassRoomId = student.getClassroom().getId();
        this.studentClassRoomGroupName = student.getClassroom().getGroupName();
        this.studentClassRoomLevel = student.getClassroom().getLevel();
    }
    long generalAvgGrade;
    TestEvalutionStatus status;
    long studentId;
    String studentFirstName;
    String studentLastName;
    String studentEmail;
    long studentClassRoomId;
    String studentClassRoomGroupName;
    ClassRoomLevel studentClassRoomLevel;
    List<getTestEvalutionGradeAvgItemResponse> testEvalutionsAvgGrades;

}
