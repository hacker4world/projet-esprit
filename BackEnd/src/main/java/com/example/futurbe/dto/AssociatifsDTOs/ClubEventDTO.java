package com.example.futurbe.dto.AssociatifsDTOs;

import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.entitys.AssociatifsEntity.EventType;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubEventDTO {
    private long id;
    private String title;
    private String description;
    private int totalPlaces;
    private int remainingPlaces;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime eventDate;
    private String location;
    private int duration;
    private EventType eventType;
    private int price;
    private ClubDTO club; // Utilisation de ClubDTO
    private RoomReservationDTO roomReservation;
}

