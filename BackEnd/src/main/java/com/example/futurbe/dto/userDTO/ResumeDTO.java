package com.example.futurbe.dto.userDTO;

import lombok.Data;
import java.util.Date;

@Data
public class ResumeDTO {
    private Long id;
    private String email;
    private Date dateOfBirth;
    private String education;
    private String experience;
    private String skils;
    private String langue;
    private UserDTO userDTO;
}
