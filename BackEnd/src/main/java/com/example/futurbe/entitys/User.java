package com.example.futurbe.entitys;

import com.example.futurbe.entitys.AssociatifsEntity.Club;
import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.entitys.AssociatifsEntity.EventRegister;
import com.example.futurbe.entitys.AssociatifsEntity.RoomReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private Date createdDate;

    private Date updatedDate;

    private String profilePicURI;

    private String resumeURI;

    @Enumerated(EnumType.STRING)
    private Option userOption;

    // New field: Consecutive failed login attempts
    private int LoginAttempts;

    // New field: Auto-generated username
    @Column() // Ensure uniqueness for usernames
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private Resume resume;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    ClassRoom classroom;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<TestEvalution> testEvalutions;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reclamation> reclamations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClubMember> clubMembers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventRegister> eventRegisters;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomReservation> roomReservations;

    @OneToMany(mappedBy = "president", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Club> presidedClubs;

    ///saber books
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ReservationBook> reservationBooks;
    ///////
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @PrePersist // Execute before saving a new user
    public void prePersist() {
        // Generate username
        username = firstName.substring(0, 1) + lastName.charAt(0) +
                new SimpleDateFormat("MM-yyyy").format(new Date()); // Month and year
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
    public void updateLoginAttempts(boolean loginSuccessful) {
        if (loginSuccessful) {
            LoginAttempts = 0; // Reset on successful login
        } else {
            LoginAttempts++;
        }
    }
    @Override
    public boolean isAccountNonLocked() {
        return LoginAttempts <= 3; // Check if account is locked based on attempts
    }
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
