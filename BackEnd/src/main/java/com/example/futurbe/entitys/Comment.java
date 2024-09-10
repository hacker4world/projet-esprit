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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private User user;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "comments", nullable = false)
    private Reclamation reclamation;
    @ElementCollection
    private List<String> attachments;
}
