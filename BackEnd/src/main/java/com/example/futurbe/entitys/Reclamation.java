package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    private ReclamationStatus status;
    private Number rate;
    @Enumerated(EnumType.STRING)
    private ReclamationType type;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
