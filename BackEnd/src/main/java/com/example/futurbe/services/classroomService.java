package com.example.futurbe.services;

import com.example.futurbe.dto.classRoomDTOs.*;
import com.example.futurbe.entitys.ClassRoom;
import com.example.futurbe.entitys.Role;
import com.example.futurbe.entitys.User;
import com.example.futurbe.repositorys.ClassroomRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.iclassroomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class classroomService implements iclassroomService {

    ClassroomRepository _classroomRepo;
    UserRepository _userRepo;

    @Override
    public GetAllClassRoomPagedResponse getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ClassRoom> classroomPage = _classroomRepo.findAll(pageRequest);

        List<getAllClassRoomResponse> classrooms = classroomPage.getContent().stream()
                .map(getAllClassRoomResponse::new)
                .collect(Collectors.toList());

        int totalPages = classroomPage.getTotalPages();

        return new GetAllClassRoomPagedResponse(totalPages, pageNumber, pageSize, classrooms);
    }

    @Override
    public GetAllClassRoomPagedResponse searchClassrooms(String groupName, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ClassRoom> classroomPage = _classroomRepo.findByGroupNameContainingIgnoreCase(groupName, pageRequest);

        List<getAllClassRoomResponse> classrooms = classroomPage.getContent().stream()
                .map(getAllClassRoomResponse::new)
                .collect(Collectors.toList());

        int totalPages = classroomPage.getTotalPages();

        return new GetAllClassRoomPagedResponse(totalPages, pageNumber, pageSize, classrooms);
    }

    @Override
    public GetClassRoomByIdResponse getById(Long id) {
        ClassRoom classroom = _classroomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
        return new GetClassRoomByIdResponse(classroom);
    }

    @Override
    public postClassRoomResponse post(postClassRoomRequest request) {
        ClassRoom classroom = new ClassRoom();
        classroom.setGroupName(request.getGroupName());
        classroom.setLevel(request.getLevel());
        classroom.setCreatedDate(new Date());
        classroom.setUpdatedDate(new Date());

        ClassRoom savedClassroom = _classroomRepo.save(classroom);

        return new postClassRoomResponse(savedClassroom);
    }

    @Override
    public List<getUnAssignedUserToClassroomResponse> getUnassignedStudents() {
        List<User> students = _userRepo.findByRoleAndClassroomIsNull(Role.STUDENT);
        return students.stream()
                .map(getUnAssignedUserToClassroomResponse::new)
                .collect(Collectors.toList());
    }
    @Override
    public void assignUserToClassroom(Long userId, Long classroomId) {

        User user = _userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ClassRoom classroom = _classroomRepo.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        user.setClassroom(classroom);
        _userRepo.save(user);
    }

    @Override
    public void removeStudentFromClassroom(Long userId) {
        User user = _userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.STUDENT) {
            throw new RuntimeException("User is not a student");
        }

        user.setClassroom(null);
        _userRepo.save(user);
    }

    @Override
    public putClassRoomResponse put(putClassRoomRequest request) {
        ClassRoom classroom = _classroomRepo.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        classroom.setGroupName(request.getGroupName());
        classroom.setLevel(request.getLevel());
        classroom.setUpdatedDate(new Date());

        ClassRoom updatedClassroom = _classroomRepo.save(classroom);

        return new putClassRoomResponse(updatedClassroom);
    }

    @Override
    public getUserByClassroomPagedResponse getUsersByClassroom(Long classroomId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize); // PageRequest pages are 0-indexed
        Page<User> userPage = _userRepo.findByClassroomId(classroomId, pageRequest);

        List<getUserByClassroomResponse> userResponses = userPage.getContent().stream()
                .map(getUserByClassroomResponse::new)
                .collect(Collectors.toList());

        return new getUserByClassroomPagedResponse(
                userPage.getTotalPages(),
                pageNumber,
                pageSize,
                userResponses
        );
    }
}
