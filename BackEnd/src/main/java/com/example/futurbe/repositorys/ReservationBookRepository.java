package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.ReservationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationBookRepository extends JpaRepository<ReservationBook, Long> {
    List<ReservationBook> findByBookId(Long bookId);
    List<ReservationBook> findByUserId(Long userId);
    @Query("SELECT r FROM ReservationBook r JOIN r.book b WHERE r.user.id = :userId AND r.isReturned = false AND b.state = true ")
    Optional<ReservationBook> findByUserIdAndReturnedIsFalse(@Param("userId") Long userId);
//    Optional<ReservationBook> findByUserIdAndReturnedFalse(Long userId);
@Query("SELECT r FROM ReservationBook r WHERE r.user.id = :userId AND r.isApproved = false AND r.requestReturn = false AND r.isReturned = false")
Optional<ReservationBook> findByUserIdAndApprovedIsFalse(@Param("userId") Long userId);

    @Query("SELECT rb FROM ReservationBook rb JOIN FETCH rb.book ")
    List<ReservationBook> findPendingReservationsWithBookDetails();

    //get the books accepted for one user
    @Query("SELECT r FROM ReservationBook r JOIN FETCH r.book WHERE r.user.id = :userId AND r.isApproved = true")
    List<ReservationBook>  findAcceptedReservationsByUserId(Long userId);
    @Query("SELECT rb FROM ReservationBook rb JOIN FETCH rb.book WHERE rb.startDate = :currentDate ")
    List<ReservationBook> findReservationsForToday(LocalDate currentDate);

    //calculer les reservation by book_id
    @Query("SELECT rb.book.id, COUNT(rb) FROM ReservationBook rb GROUP BY rb.book.id")
    List<Object[]> countReservationsByBook();
}
