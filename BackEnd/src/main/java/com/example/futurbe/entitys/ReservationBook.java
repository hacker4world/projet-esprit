package com.example.futurbe.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import com.example.futurbe.dto.ReservationBookDTO;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reservation_book")
public class ReservationBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 1000)
    private String reason;

    @Column(nullable = false)
    private boolean isApproved = false;

    @Column(nullable = false)
    private boolean isReturned = false;
    @Column(nullable = false)
    private boolean requestReturn = false;
    @Column(nullable = true)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    public ReservationBookDTO getDto() {
        ReservationBookDTO reservationBookDTO = new ReservationBookDTO();
        reservationBookDTO.setId(id);
        reservationBookDTO.setReason(reason);
        reservationBookDTO.setApproved(isApproved);
        reservationBookDTO.setReturned(isReturned);
        reservationBookDTO.setStartDate(startDate);
        reservationBookDTO.setEndDate(endDate);
        reservationBookDTO.setUserId(user.getId());
        reservationBookDTO.setBookId(book.getId());
        reservationBookDTO.setRequestReturn(requestReturn);
        return reservationBookDTO;
    }
}
