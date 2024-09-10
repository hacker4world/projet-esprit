package com.example.futurbe.services.iservices.AssociatifsIServices;

import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;

import java.util.List;
import java.util.Optional;

public interface IClubEventService {
    List<ClubEvent> getAllEvents();
    Optional<ClubEvent> getEventById(Long id);
    ClubEvent saveEvent(ClubEvent event);
    void deleteEvent(Long id);
}
