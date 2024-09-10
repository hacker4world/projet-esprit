package com.example.futurbe.dto.classRoomDTOs;

import com.example.futurbe.entitys.ClassRoom;
import com.example.futurbe.entitys.ClassRoomLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class putClassRoomResponse {

    public putClassRoomResponse(ClassRoom classRoom) {
        id = classRoom.getId();
        groupName = classRoom.getGroupName();
        level = classRoom.getLevel();
        createdDate = classRoom.getCreatedDate();
        updatedDate = classRoom.getUpdatedDate();
    }

    long id;
    String groupName;
    @Enumerated(EnumType.STRING)
    ClassRoomLevel level;
    Date createdDate;
    Date updatedDate;
}
