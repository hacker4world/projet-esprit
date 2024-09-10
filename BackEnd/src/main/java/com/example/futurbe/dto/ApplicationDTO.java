package com.example.futurbe.dto;
import java.util.Date;

public class ApplicationDTO {
    private Long id;
    private Long offerId;
    private Long userId;
    private Date applicationDate;
    private boolean is_viewed ;

    // Constructors, getters, setters

    public ApplicationDTO() {
        // Default constructor
    }

    public ApplicationDTO(Long id, Long offerId, Long userId, Date applicationDate,boolean is_viewed) {
        this.id = id;
        this.offerId = offerId;
        this.userId = userId;
        this.applicationDate = applicationDate;
        this.is_viewed = is_viewed;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public boolean isIs_viewed() {
        return is_viewed;
    }

    public void setIs_viewed(boolean is_viewed) {
        this.is_viewed = is_viewed;
    }
}
