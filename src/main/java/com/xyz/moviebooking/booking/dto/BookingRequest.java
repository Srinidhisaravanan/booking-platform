package com.xyz.moviebooking.booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookingRequest {
    
    @NotNull
    private Long showId;
    
    @NotEmpty
    private List<Long> seatIds;
    
    @NotBlank
    @Email
    private String userEmail;
    
    private String userPhone;

    // Constructors
    public BookingRequest() {}

    public BookingRequest(Long showId, List<Long> seatIds, String userEmail, String userPhone) {
        this.showId = showId;
        this.seatIds = seatIds;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    // Getters and Setters
    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}