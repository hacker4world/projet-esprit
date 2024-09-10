package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.classRoomDTOs.*;

import java.util.List;

public interface iclassroomService {
    GetAllClassRoomPagedResponse getAll(int pageNumber, int pageSize);
    GetAllClassRoomPagedResponse searchClassrooms(String groupName, int pageNumber, int pageSize);
    GetClassRoomByIdResponse getById(Long id);
    postClassRoomResponse post(postClassRoomRequest request);
    List<getUnAssignedUserToClassroomResponse> getUnassignedStudents();
    void removeStudentFromClassroom(Long userId);
    void assignUserToClassroom(Long userId, Long classroomId);
    putClassRoomResponse put(putClassRoomRequest request);
    getUserByClassroomPagedResponse getUsersByClassroom(Long classroomId, int pageNumber, int pageSize);
}
