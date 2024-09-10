package com.example.futurbe.entitys;

import com.example.futurbe.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String description;
    private int nbPage;
    private Boolean state ;
    private boolean recommanded = false;
    private String imageUrl;
    private float price;
    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CategoryBook category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<ReservationBook> reservationBooks;


    public BookDTO getDto(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(id);
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookDTO.setDescription(description);
        bookDTO.setState(state);
        bookDTO.setImageUrl(imageUrl);
        bookDTO.setPrice(price);
        bookDTO.setNbPage(nbPage);
        bookDTO.setCategoryId(category.getId());
        bookDTO.setRecommended(recommanded);
        // Fetch the latest reservation status and end date
        if (reservationBooks != null && !reservationBooks.isEmpty()) {
            ReservationBook latestReservation = reservationBooks.stream()
                    .findFirst()
                    .orElse(null);
               bookDTO.setIsApproved(latestReservation.isApproved());
               bookDTO.setIsReturned(latestReservation.isReturned());
            bookDTO.setRequestReturn(latestReservation.isRequestReturn());
               bookDTO.setEndDate(latestReservation.getEndDate());

        }
        return bookDTO;
    }
}
