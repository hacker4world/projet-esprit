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
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Date datePublication;
    private Date dateMaj;
    private boolean isStillAvailable;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre ;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Offre() {

    }
}
