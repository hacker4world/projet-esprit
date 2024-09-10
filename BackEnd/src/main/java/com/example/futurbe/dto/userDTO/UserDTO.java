package com.example.futurbe.dto.userDTO;

import com.example.futurbe.entitys.Option;
import com.example.futurbe.entitys.Role;
import lombok.Data;
import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String password;
    private String lastName;
    private Date createdDate;
    private Date updatedDate;
    private Option userOption;
    private  String username;
    private int LoginAttempts;

    private String email;

    private String profilePicURI;
    private String resumeURI;
    private Role role;
    private ResumeDTO resume;

}
