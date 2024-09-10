package com.example.futurbe.mapper.Documents;

import com.example.futurbe.dto.Documents.DocumentDTO;
import com.example.futurbe.dto.Documents.DocumentGetAllResponse;
import com.example.futurbe.dto.Documents.UserResponseDTO;
import com.example.futurbe.entitys.Document;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentResponseGetAll {
    public DocumentGetAllResponse convertToDto(Document document) {
        DocumentGetAllResponse documentDTO = new DocumentGetAllResponse();
        documentDTO.setId(document.getId());
        documentDTO.setType(document.getType());
        documentDTO.setUpdatedAt(document.getUpdatedAt());
        documentDTO.setStatus(document.getStatus());
        UserResponseDTO userDTO = new UserResponseDTO(document.getUser().getFirstName(), document.getUser().getLastName(),document.getUser().getEmail(),document.getUser().getId());
        documentDTO.setUser(userDTO);        documentDTO.setCreatedAt(document.getCreatedAt());
        documentDTO.setComment(document.getComment());

        return documentDTO;
    }

    public Document convertToEntity(DocumentGetAllResponse documentDTO, User user) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setType(documentDTO.getType());
        document.setUpdatedAt(documentDTO.getUpdatedAt());
        document.setStatus(documentDTO.getStatus());
        UserResponseDTO userDTO = new UserResponseDTO(document.getUser().getFirstName(), document.getUser().getLastName(),document.getUser().getEmail(),document.getUser().getId());
        documentDTO.setUser(userDTO);
        document.setCreatedAt(documentDTO.getCreatedAt());
        document.setComment(documentDTO.getComment());
        return document;
    }
}
