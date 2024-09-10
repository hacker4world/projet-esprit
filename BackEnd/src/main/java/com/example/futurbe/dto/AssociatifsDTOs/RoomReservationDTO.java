package com.example.futurbe.dto.AssociatifsDTOs;

import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.AssociatifsEntity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomReservationDTO {
    private long id;
    private String roomNumber;
    private LocalDateTime createdDate;
    private LocalDateTime reservedDate;
    private RequestStatus status;
    private UserDTO user; // Utilisation de UserDTO
    private String purpose;
    private int numberOfAttendees;
    private ClubEventDTO clubEvent; // Utilisation de ClubEventDTO

    public void setUser(Long aLong) {
    }

    public void setEventId(Long aLong) {

    }
}
