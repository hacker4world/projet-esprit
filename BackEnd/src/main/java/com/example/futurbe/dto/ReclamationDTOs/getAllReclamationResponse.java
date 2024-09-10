package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.dto.Documents.UserResponseDTO;
import com.example.futurbe.entitys.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class getAllReclamationResponse {

    public getAllReclamationResponse(Reclamation reclamation){
        id = reclamation.getId();
        type = reclamation.getType();
        status = reclamation.getStatus();
        updatedAt = reclamation.getUpdatedAt();
        createdAt = reclamation.getCreatedAt();
        UserResponseDTO userDTO = new UserResponseDTO(reclamation.getUser().getFirstName(), reclamation.getUser().getLastName(),reclamation.getUser().getEmail(),reclamation.getUser().getId());
        user = userDTO;
        subject= reclamation.getSubject();

    }

    private Long id;
    private ReclamationType type;
    private Date updatedAt;
    private ReclamationStatus status;
    private UserResponseDTO user;
    private Date createdAt;
    private String comment;
    private String subject;

}
