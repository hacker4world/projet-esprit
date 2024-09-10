package com.example.futurbe.services;

import com.example.futurbe.dto.ReservationBookDTO;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Book;
import com.example.futurbe.entitys.ReservationBook;
import com.example.futurbe.repositorys.BookRepository;
import com.example.futurbe.repositorys.ReservationBookRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IReservationBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationBookServiceImpl implements IReservationBookService {
    private final ReservationBookRepository reservationBookRepository;
    private final UserRepository userRepository;
    private  final BookRepository bookRepository;
    private final EmailService emailService;
    @Override
    public ReservationBookDTO reserveBook(Long userId, Long bookId ,ReservationBookDTO reservationBookDTO) {
        //vérifier que luser n'a pas de reservation active
        boolean hasActiveReservation = reservationBookRepository.findByUserIdAndReturnedIsFalse(userId)
                .isPresent();
        if(hasActiveReservation)
        {
            throw new IllegalStateException("l'utilisateur a déja une reservation en cours ");
        }
        boolean hasPendingReservation = reservationBookRepository.findByUserIdAndApprovedIsFalse(userId).isPresent();
        if (hasPendingReservation) {
            throw new IllegalStateException("L'utilisateur a déjà une demande de réservation en attente.");
        }
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        LocalDate now = LocalDate.now();
        ReservationBook reservationBook = new ReservationBook();
        reservationBook.setReason(reservationBookDTO.getReason());
        reservationBook.setStartDate(now);
        reservationBook.setEndDate(reservationBookDTO.getEndDate());
        reservationBook.setUser(userRepository.findById(userId).orElseThrow());
        //reservationBook.setBook(bookRepository.findById(reservationBookDTO.getBookId()).orElseThrow());
        reservationBook.setBook(book);
        reservationBook.setApproved(false);
        reservationBook.setReturned(false);
        reservationBook.setRequestReturn(false);
        reservationBook = reservationBookRepository.save(reservationBook);
        return reservationBook.getDto();
    }

    @Override
    public ReservationBookDTO acceptReservation(Long reservationId) {
        ReservationBook reservationBook = reservationBookRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationBook.setApproved(true);
        reservationBook.getBook().setState(true); // Change book state to true (borrowed)
        reservationBook = reservationBookRepository.save(reservationBook);

        String userEmail = reservationBook.getUser().getEmail();
        String subject = "Reservation accepted";
        String text ="Your reservation for the book  \"" + reservationBook.getBook().getTitle() + "\" has been accepted.";
        emailService.sendEmail(userEmail, subject, text);

        return reservationBook.getDto();
    }

    @Override
    public ReservationBookDTO acceptReturn(Long reservationId) {
        ReservationBook reservationBook = reservationBookRepository.findById(reservationId).orElseThrow();
        reservationBook.setReturned(true);
        reservationBook.getBook().setState(false); // Change book state to false (available)
        return reservationBookRepository.save(reservationBook).getDto();
    }
    public ReservationBookDTO approveReturn(Long reservationId) {
        ReservationBook reservationBook = reservationBookRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationBook.setReturned(true);
        reservationBook.getBook().setState(false);
        reservationBook = reservationBookRepository.save(reservationBook);
        return reservationBook.getDto();
    }
    @Override
    public ReservationBookDTO requestReturn(Long reservationId) {
        ReservationBook reservationBook = reservationBookRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationBook.setRequestReturn(true);
        reservationBook = reservationBookRepository.save(reservationBook);
        return reservationBook.getDto();
    }



    @Override
    public List<ReservationBookDTO> getPendingReservationsWithBookDetails() {
        List<ReservationBook> pendingReservations = reservationBookRepository.findPendingReservationsWithBookDetails();
        List<ReservationBookDTO> reservationBookDTOs = new ArrayList<>();

        for (ReservationBook reservationBook : pendingReservations) {
            ReservationBookDTO reservationBookDTO = reservationBook.getDto();
            reservationBookDTO.setBook(reservationBook.getBook().getDto()); // Inclure les détails du livre
            reservationBookDTOs.add(reservationBookDTO);
        }

        return reservationBookDTOs;
    }
    public List<ReservationBookDTO> getReservationsAcceptedForOneUser(Long userId) {
        return reservationBookRepository.findAcceptedReservationsByUserId(userId).stream()
                .map(reservationBook -> {
                    ReservationBookDTO reservationBookDTO = reservationBook.getDto();
                    reservationBookDTO.setBook(reservationBook.getBook().getDto());
                    return reservationBookDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public List<ReservationBookDTO> getReservationToday(){
        LocalDate today = LocalDate.now();
        List<ReservationBook> reservations = reservationBookRepository.findReservationsForToday(today);

        List<ReservationBookDTO> todayReservation = new ArrayList<>();

        for (ReservationBook reservationBook :reservations) {
            ReservationBookDTO reservationBookDTO = reservationBook.getDto();
            reservationBookDTO.setBook(reservationBook.getBook().getDto()); // Inclure les détails du livre
            todayReservation .add(reservationBookDTO);
        }

        return todayReservation ;
    }

    public void refuseReserveBook(long id) {
        Optional<ReservationBook> reservationBookOpt = reservationBookRepository.findById(id);
        if (reservationBookOpt.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        ReservationBook reservationBook = reservationBookOpt.get();
        String userEmail = reservationBook.getUser().getEmail();
        String subject = "Reservation Not Accepted";
        String text ="Dear " + reservationBook.getUser().getFirstName() + ",\n\n" +
                "Your reservation for the book \"" + reservationBook.getBook().getTitle() + "\" has not been accepted.\n" +
                "Therefore, the reservation has been removed from our records.\n\n" +
                "Best regards,\n" +
                "Your Library Team";
        emailService.sendEmail(userEmail, subject, text);
        reservationBookRepository.deleteById(id);
    }
}
