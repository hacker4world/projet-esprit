package com.example.futurbe.dto.TestEvaluationDTOs;

import com.example.futurbe.entitys.ClassRoomLevel;
import com.example.futurbe.entitys.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class getAllTestEvalutionsByUserResponse {

    public  getAllTestEvalutionsByUserResponse(User student,List<getTestEvalutionItemByUserResponse> testEvalutionGradeAvgList ) {
        this.studentId = student.getId();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.studentEmail = student.getEmail();
        this.studentClassRoomId = student.getClassroom().getId();
        this.studentClassRoomGroupName = student.getClassroom().getGroupName();
        this.studentClassRoomLevel = student.getClassroom().getLevel();
        this.testEvalutionGradeAvgList = testEvalutionGradeAvgList;
    }
    long studentId;
    String studentFirstName;
    String studentLastName;
    String studentEmail;
    long studentClassRoomId;
    String studentClassRoomGroupName;
    @Enumerated(EnumType.STRING)
    ClassRoomLevel studentClassRoomLevel;

    List<getTestEvalutionItemByUserResponse> testEvalutionGradeAvgList;

}
