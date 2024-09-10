package com.example.futurbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private long id;
    private String title;
    private String author;
    private String description;
    private Boolean state;
    private String imageUrl;
    private float price;
    private Long categoryId;
    private int nbPage;
    private boolean recommended;
    private Boolean isReturned;
    private Boolean isApproved;
    private Boolean requestReturn;
    private LocalDate endDate;
}
