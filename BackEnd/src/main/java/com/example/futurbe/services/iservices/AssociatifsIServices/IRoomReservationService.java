package com.example.futurbe.services.iservices.AssociatifsIServices;
import com.example.futurbe.entitys.AssociatifsEntity.RoomReservation;

import java.util.List;
import java.util.Optional;

public interface IRoomReservationService {
    List<RoomReservation> getAllReservations();
    Optional<RoomReservation> getReservationById(Long id);
    RoomReservation saveReservation(RoomReservation reservation);
    void deleteReservation(Long id);
}

