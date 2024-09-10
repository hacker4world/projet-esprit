package com.example.futurbe.mapper;

import com.example.futurbe.dto.ReclamationDTOs.ReclamationDTO;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Reclamation;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationMapper {

    UserMapper userMapper;

    public ReclamationDTO convertToDto(Reclamation reclamation) {
        User user = reclamation.getUser();
        UserDTO userDTO = user != null ? userMapper.convertToDto(user) : null;

        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setId(reclamation.getId());
        reclamationDTO.setSubject(reclamation.getSubject());
        reclamationDTO.setCreatedAt(reclamation.getCreatedAt());
        reclamationDTO.setUpdatedAt(reclamation.getUpdatedAt());
        reclamationDTO.setStatus(reclamation.getStatus());
        reclamationDTO.setUser(reclamation.getUser());
        reclamationDTO.setRate(reclamation.getRate());
        reclamationDTO.setType(reclamation.getType());
        // Ajoutez le mapping des commentaires

        return reclamationDTO;
    }

    public Reclamation convertToEntity(ReclamationDTO reclamationDTO) {
        Reclamation reclamation = new Reclamation();
        reclamation.setId(reclamationDTO.getId());
        reclamation.setSubject(reclamationDTO.getSubject());
        reclamation.setCreatedAt(reclamationDTO.getCreatedAt());
        reclamation.setUpdatedAt(reclamationDTO.getUpdatedAt());
        reclamation.setStatus(reclamationDTO.getStatus());
        reclamation.setUser(reclamationDTO.getUser());
        reclamation.setRate(reclamationDTO.getRate());
        reclamation.setType(reclamationDTO.getType());

        return reclamation;
    }
}
