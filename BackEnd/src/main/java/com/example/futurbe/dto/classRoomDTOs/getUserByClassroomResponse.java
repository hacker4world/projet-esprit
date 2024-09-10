package com.example.futurbe.dto.classRoomDTOs;


import com.example.futurbe.entitys.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class getUserByClassroomResponse {

    public  getUserByClassroomResponse(User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        createdDate = user.getCreatedDate();
        profilePicURI = user.getProfilePicURI();
    }
    Long id;
    String firstName;
    String lastName;
    Date createdDate;
    String profilePicURI;
}
