package com.example.futurbe.services.iservices;
import java.util.List;
import com.example.futurbe.dto.ReservationBookDTO;
public interface IReservationBookService {
    ReservationBookDTO reserveBook(Long userId, Long bookId ,ReservationBookDTO reservationBookDTO);
    // Accept a reservation request
    ReservationBookDTO acceptReservation(Long reservationId);

    // Accept a return request
    ReservationBookDTO acceptReturn(Long reservationId);
    // Demande le retour d un livre
    ReservationBookDTO requestReturn(Long reservationId);
    //Accepte une demande de retour de livre
    ReservationBookDTO approveReturn(Long reservationId);
    List<ReservationBookDTO> getPendingReservationsWithBookDetails();

    List<ReservationBookDTO> getReservationsAcceptedForOneUser(Long userId);

    List<ReservationBookDTO> getReservationToday();
    void refuseReserveBook(long id);
}
