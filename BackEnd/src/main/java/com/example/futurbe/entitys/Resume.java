package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private Date dateOfBirth;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private String experiance;

    @Column(nullable = false)
    private String skils;

    @Column(nullable = false)
    private String langue;

    @OneToOne
    @JoinColumn(name = "resume", nullable = true)
    private User user;
}
