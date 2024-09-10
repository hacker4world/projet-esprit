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
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id;

    String groupName;
    @Enumerated(EnumType.STRING)
    ClassRoomLevel level;


    @Temporal(TemporalType.DATE)
    Date createdDate;
    @Temporal(TemporalType.DATE)
    Date updatedDate;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    Set<User> students;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    Set<Subject> subjects;


}
