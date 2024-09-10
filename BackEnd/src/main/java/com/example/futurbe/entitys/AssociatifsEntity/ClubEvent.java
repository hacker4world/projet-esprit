package com.example.futurbe.entitys.AssociatifsEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "club_events")
public class ClubEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "total_places", nullable = false)
    private int totalPlaces;

    @Column(name = "remaining_places", nullable = false)
    private int remainingPlaces;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // Set default value

    @Column(name = "updated_date")
    private LocalDateTime updatedDate = LocalDateTime.now();


    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "duration", nullable = false)
    private int duration;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;


    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RoomReservation roomReservation;

}
