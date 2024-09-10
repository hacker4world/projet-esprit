package com.example.futurbe.dto.classRoomDTOs;

import com.example.futurbe.entitys.ClassRoom;
import com.example.futurbe.entitys.ClassRoomLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetClassRoomByIdResponse {

    private long id;
    private String groupName;
    private ClassRoomLevel level;
    private Date createdDate;
    private Date updatedDate;

    public GetClassRoomByIdResponse(ClassRoom classRoom) {
        this.id = classRoom.getId();
        this.groupName = classRoom.getGroupName();
        this.level = classRoom.getLevel();
        this.createdDate = classRoom.getCreatedDate();
        this.updatedDate = classRoom.getUpdatedDate();
    }
}
