package com.example.futurbe.dto.Documents;

import com.example.futurbe.entitys.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class getAllDocumentResponse {

    public getAllDocumentResponse(Document document){
        id = document.getId();
        type = document.getType();
        status = document.getStatus();
        updatedAt = document.getUpdatedAt();
        createdAt = document.getCreatedAt();
        UserResponseDTO userDTO = new UserResponseDTO(document.getUser().getFirstName(), document.getUser().getLastName(),document.getUser().getEmail(),document.getUser().getId());
        user = userDTO;
        comment =document.getComment();
    }

    private Long id;
    private DocumentType type;
    private Date updatedAt;
    private DocumentStatus status;
    private UserResponseDTO user;
    private Date createdAt;
    private String comment;
}
