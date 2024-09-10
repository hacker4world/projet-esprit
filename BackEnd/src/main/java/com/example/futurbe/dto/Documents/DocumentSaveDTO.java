package com.example.futurbe.dto.Documents;

import com.example.futurbe.entitys.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentSaveDTO {
    private DocumentType type;
    private String comment;
}
