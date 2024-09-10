package com.example.futurbe.entitys;

import com.example.futurbe.dto.FeedbackDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double note;
    private String comment;
    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    public FeedbackDTO getDto() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(id);
        feedbackDTO.setNote(note);
        feedbackDTO.setComment(comment);
        feedbackDTO.setBookId(book.getId());
        return feedbackDTO;
    }
}
