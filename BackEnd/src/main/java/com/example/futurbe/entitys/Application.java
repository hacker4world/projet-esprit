package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offre offer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date applicationDate;
    private boolean is_viewed ;

    public Application() {

    }

    // Getters and setters
}