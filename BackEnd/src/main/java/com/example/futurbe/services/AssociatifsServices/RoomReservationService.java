package com.example.futurbe.services.AssociatifsServices;

import com.example.futurbe.entitys.AssociatifsEntity.RoomReservation;
import com.example.futurbe.repositorys.AssociatifsRepo.RoomReservationRepository;
import com.example.futurbe.services.iservices.AssociatifsIServices.IRoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomReservationService implements IRoomReservationService {

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Override
    public List<RoomReservation> getAllReservations() {
        return roomReservationRepository.findAll();
    }

    @Override
    public Optional<RoomReservation> getReservationById(Long id) {
        return roomReservationRepository.findById(id);
    }

    @Override
    public RoomReservation saveReservation(RoomReservation reservation) {
        return roomReservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }
}