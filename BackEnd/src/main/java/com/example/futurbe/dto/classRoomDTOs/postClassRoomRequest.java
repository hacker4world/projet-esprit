package com.example.futurbe.dto.classRoomDTOs;

import com.example.futurbe.entitys.ClassRoomLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class postClassRoomRequest {
    String groupName;
    @Enumerated(EnumType.STRING)
    ClassRoomLevel level;
}
