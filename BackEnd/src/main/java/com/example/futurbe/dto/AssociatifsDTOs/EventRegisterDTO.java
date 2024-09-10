package com.example.futurbe.dto.AssociatifsDTOs;

import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.AssociatifsEntity.RequestStatus;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterDTO {
    private long id;
    private LocalDateTime registerDate;
    private ClubEvent clubEvent; // Utilisation de ClubEventDTO
    private UserDTO user; // Utilisation de UserDTO
    private RequestStatus status;
    private String feedback;

    public void setEventId(Long aLong) {
    }

    public void setUserId(Long aLong) {
    }

    public long getEventId() {
        return 0;
    }
}
