package com.example.futurbe.mapper.Documents;

import com.example.futurbe.dto.Documents.DocumentDTO;
import com.example.futurbe.entitys.Document;
import com.example.futurbe.entitys.User;
import com.example.futurbe.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class DocumentMapper {

    private UserMapper userMapper;

    public DocumentDTO convertToDto(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setType(document.getType());
        documentDTO.setUpdatedAt(document.getUpdatedAt());
        documentDTO.setStatus(document.getStatus());
        //documentDTO.setUser(document.getUser());
        documentDTO.setCreatedAt(document.getCreatedAt());
        documentDTO.setComment(document.getComment());

        return documentDTO;
    }

    public Document convertToEntity(DocumentDTO documentDTO, User user) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setType(documentDTO.getType());
        document.setUpdatedAt(documentDTO.getUpdatedAt());
        document.setStatus(documentDTO.getStatus());
        //document.setUser(user);
        document.setCreatedAt(documentDTO.getCreatedAt());
        document.setComment(documentDTO.getComment());
        return document;
    }
}

