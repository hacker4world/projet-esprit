package com.example.futurbe.dto.Documents;

import com.example.futurbe.entitys.DocumentStatus;
import com.example.futurbe.entitys.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentGetAllResponse {
    private Long id;
    private DocumentType type;
    private Date updatedAt;
    private DocumentStatus status;
    private UserResponseDTO user;
    private Date createdAt;
    private String comment;
}
