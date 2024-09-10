package com.example.futurbe.controller.AssociatifsController;

import com.example.futurbe.entitys.AssociatifsEntity.RoomReservation;
import com.example.futurbe.services.AssociatifsServices.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationController {

    @Autowired
    private RoomReservationService roomReservationService;

    @GetMapping
    public ResponseEntity<List<RoomReservation>> getAllReservations() {
        List<RoomReservation> reservations = roomReservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomReservation> getReservationById(@PathVariable Long id) {
        Optional<RoomReservation> reservation = roomReservationService.getReservationById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoomReservation> createReservation(@RequestBody RoomReservation reservation) {
        RoomReservation createdReservation = roomReservationService.saveReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomReservation> updateReservation(@PathVariable Long id, @RequestBody RoomReservation reservation) {
        if (!roomReservationService.getReservationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reservation.setId(id);
        RoomReservation updatedReservation = roomReservationService.saveReservation(reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!roomReservationService.getReservationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        roomReservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}

