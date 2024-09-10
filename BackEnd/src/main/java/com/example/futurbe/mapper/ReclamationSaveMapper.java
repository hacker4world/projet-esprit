package com.example.futurbe.mapper;


import com.example.futurbe.dto.ReclamationDTOs.ReclamationSave;
import com.example.futurbe.entitys.Reclamation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReclamationSaveMapper {
    public ReclamationSave convertToDto(Reclamation reclamation) {
        ReclamationSave claimDTO = new ReclamationSave();
        claimDTO.setType(reclamation.getType());
        claimDTO.setDescription(reclamation.getDescription());
        claimDTO.setSubject(reclamation.getSubject());
        return claimDTO;
    }
    public Reclamation convertToEntity(ReclamationSave reclamationDTO) {
        Reclamation claim = new Reclamation();
        claim.setType(reclamationDTO.getType());
        claim.setDescription(reclamationDTO.getDescription());
        claim.setSubject(reclamationDTO.getSubject());
        return claim;
    }

    }
