package com.example.futurbe.dto.AssociatifsDTOs;

import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String logoURI;
    private UserDTO president; // Utilisation de UserDTO
    private int totalMembers;
    private String email;
    private String phone;
    private List<ClubMemberDTO> members;
    private List<ClubEventDTO> events;

    public void setPresidentId(Object presidentId) {
    }

    public Object getPresidentId() {
        return null;
    }
}
