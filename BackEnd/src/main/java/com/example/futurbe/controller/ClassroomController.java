package com.example.futurbe.controller;

import com.example.futurbe.dto.classRoomDTOs.*;
import com.example.futurbe.services.iservices.iclassroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Classroom")
public class ClassroomController {

    iclassroomService classroomService;

    @GetMapping("/search")
    public GetAllClassRoomPagedResponse searchClassrooms(@RequestParam String groupName,@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return classroomService.searchClassrooms(groupName,pageNumber-1, pageSize);
    }
    @GetMapping
    @ResponseBody
    public GetAllClassRoomPagedResponse getAll(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return classroomService.getAll(pageNumber-1, pageSize);
    }

    @GetMapping("/{id}")
    public GetClassRoomByIdResponse getClassroomById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PostMapping
    public postClassRoomResponse postClassroom(@RequestBody postClassRoomRequest request) {
        return classroomService.post(request);
    }
    @PutMapping("assignUser/{userId}/{classroomId}")
    public void assignUserToClassroom(@PathVariable Long userId, @PathVariable Long classroomId) {
        classroomService.assignUserToClassroom(userId, classroomId);
    }

    @PutMapping
    public putClassRoomResponse updateClassroom(@RequestBody putClassRoomRequest request) {
        return classroomService.put(request);
    }

    @GetMapping("/unassigned-students")
    public List<getUnAssignedUserToClassroomResponse> getUnassignedStudents() {
        return classroomService.getUnassignedStudents();
    }

    @PutMapping("/removeStudent/{userId}")
    public void removeStudentFromClassroom(@PathVariable Long userId) {
        classroomService.removeStudentFromClassroom(userId);
    }

    @GetMapping("/user/{classroomId}")
    public getUserByClassroomPagedResponse getUsersByClassroom(
            @PathVariable Long classroomId,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return classroomService.getUsersByClassroom(classroomId, pageNumber - 1, pageSize);
    }

}
