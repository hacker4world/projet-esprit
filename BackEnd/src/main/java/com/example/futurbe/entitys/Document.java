package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    DocumentStatus status;
    @ManyToOne
    private User user;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(nullable = true)
    private String comment;
}
