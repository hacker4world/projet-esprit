package com.example.futurbe.dto.Documents;

import com.example.futurbe.entitys.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUpdateDTO {
    private DocumentStatus status;
    private String comment;
    private Long documentId;
}
