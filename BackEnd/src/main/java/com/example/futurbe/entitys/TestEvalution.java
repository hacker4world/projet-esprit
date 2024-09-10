package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity

@Getter
@Setter
@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestEvalution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id;

    float note;

    @Temporal(TemporalType.DATE)
    Date createdDate;
    @Temporal(TemporalType.DATE)
    Date updatedDate;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    User user;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    SubjectTest subjectTest;

}
