package com.example.futurbe.entitys.AssociatifsEntity;

import com.example.futurbe.entitys.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_reservations")
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "reserved_date", nullable = false)
    private LocalDateTime reservedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "number_of_attendees", nullable = false)
    private int numberOfAttendees;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private ClubEvent event;
}