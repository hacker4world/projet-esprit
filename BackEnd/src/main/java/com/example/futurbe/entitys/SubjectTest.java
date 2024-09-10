package com.example.futurbe.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity

@Getter
@Setter
@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id;

    @Enumerated(EnumType.STRING)
    SubjectTestType testType;

    float coefficient;

    @Temporal(TemporalType.DATE)
    Date createdDate;
    @Temporal(TemporalType.DATE)
    Date updatedDate;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "subjectTest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<TestEvalution> testEvalutions;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    Subject subject;

}
