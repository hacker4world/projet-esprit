package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity

@Getter
@Setter
@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id;
    String Name;

    int totalHours;

    float coefficient;

    @Temporal(TemporalType.DATE)
    Date createdDate;
    @Temporal(TemporalType.DATE)
    Date updatedDate;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<SubjectTest> subjectTests;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    ClassRoom classroom;

}
