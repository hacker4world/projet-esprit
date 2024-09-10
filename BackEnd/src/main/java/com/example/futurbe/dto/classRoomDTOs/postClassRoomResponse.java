package com.example.futurbe.dto.classRoomDTOs;

import com.example.futurbe.entitys.ClassRoom;
import com.example.futurbe.entitys.ClassRoomLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class postClassRoomResponse {
    public postClassRoomResponse(ClassRoom classRoom) {
        id = classRoom.getId();
        groupName = classRoom.getGroupName();
        level = classRoom.getLevel();
        createdDate = classRoom.getCreatedDate();
    }
    long id;
    String groupName;
    ClassRoomLevel level;
    Date createdDate;
}
