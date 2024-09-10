package com.example.futurbe.controller;

import com.example.futurbe.dto.ReservationBookDTO;
import com.example.futurbe.services.iservices.IReservationBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
@CrossOrigin("*")
public class ReservationBookController {
    private final IReservationBookService reservationBookService;

    @PostMapping("/")
    public ResponseEntity<ReservationBookDTO> reserveBook(@RequestParam Long userId,
                                                          @RequestParam Long bookId,
                                                          @RequestBody ReservationBookDTO reservationBookDTO) {
        ReservationBookDTO reservation = reservationBookService.reserveBook(userId, bookId, reservationBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<ReservationBookDTO>> getPendingReservationsWithBookDetails() {
        List<ReservationBookDTO> pendingReservations = reservationBookService.getPendingReservationsWithBookDetails();
        return ResponseEntity.ok(pendingReservations);
    }
    @PutMapping("/accept-reservation/{reservationId}")
    public ResponseEntity<ReservationBookDTO> acceptReservation(@PathVariable Long reservationId) {
        ReservationBookDTO acceptedReservation = reservationBookService.acceptReservation(reservationId);
        return ResponseEntity.ok(acceptedReservation);
    }

    // Accept a return request
    @PutMapping("/accept-return/{reservationId}")
    public ResponseEntity<ReservationBookDTO> acceptReturn(@PathVariable Long reservationId) {
        ReservationBookDTO acceptedReturn = reservationBookService.acceptReturn(reservationId);
        return ResponseEntity.ok(acceptedReturn);
    }

    @PutMapping("/{reservationId}/request-return")
    public ResponseEntity<ReservationBookDTO> requestReturn(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationBookService.requestReturn(reservationId));
    }
    @PutMapping("/{reservationId}/approve-return")
    public ResponseEntity<ReservationBookDTO> approveReturn(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationBookService.approveReturn(reservationId));
    }

    @GetMapping("/accepted/{userId}")
    public ResponseEntity<List<ReservationBookDTO>> getReservationsAcceptedForOneUser(@PathVariable Long userId) {
        List<ReservationBookDTO> acceptedReservations = reservationBookService.getReservationsAcceptedForOneUser(userId);
        return ResponseEntity.ok(acceptedReservations);
    }
    @GetMapping("/today/not-returned")
    public ResponseEntity<List<ReservationBookDTO>> getReservationsForTodayAndNotReturned() {
        List<ReservationBookDTO> reservations = reservationBookService.getReservationToday();
        return ResponseEntity.ok(reservations);
    }
    @DeleteMapping("/refuse-book/{id}")
    public ResponseEntity<Void> refuseReservation(@PathVariable long id) {
        reservationBookService.refuseReserveBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
