package com.example.futurbe.dto.AssociatifsDTOs;

import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.AssociatifsEntity.Club;
import com.example.futurbe.entitys.AssociatifsEntity.RoleMember;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubMemberDTO {
    private long id;
    private LocalDateTime joinDate;
    private ClubDTO club; // Utilisation de ClubDTO
    private UserDTO user; // Utilisation de UserDTO
    private RoleMember role;
    private String email;
    private int CIN;
    private String phone;
    private String fullName; // Correction du nom du champ

    public void setClubId(Long aLong) {
    }

    public void setUserId(Long aLong) {
    }

    public void setFullname(String fullname) {
    }

    public String getFullname() {
        return "";
    }

    public long getClubId() {
        return 0;
    }

    public boolean getUserId() {
        return false;
    }
}

