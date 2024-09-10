package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.entitys.DocumentStatus;
import com.example.futurbe.entitys.ReclamationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationUpdateStatusDTO {
    private ReclamationStatus status;
    private Long claimId;
}
