package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.entitys.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationUpdate {
    private String comment;
    private Long claimId;
    private Long userId;
}
