package com.example.futurbe.mapper.Documents;

import com.example.futurbe.dto.Documents.DocumentSaveDTO;
import com.example.futurbe.entitys.Document;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentSaveMapper {
    public DocumentSaveDTO convertToDto(Document document) {
        DocumentSaveDTO documentDTO = new DocumentSaveDTO();
        documentDTO.setType(document.getType());
        documentDTO.setComment(document.getComment());
        return documentDTO;
    }

    public Document convertToEntity(DocumentSaveDTO documentDTO) {
        Document document = new Document();
        document.setType(documentDTO.getType());
        document.setComment(documentDTO.getComment());
        return document;
    }
}
