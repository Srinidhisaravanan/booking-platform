package com.xyz.moviebooking.movie.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShowResponse {
    private Long id;
    private String movieTitle;
    private String theatreName;
    private String screenName;
    private String screenType;
    private LocalDateTime showDateTime;
    private BigDecimal basePrice;
    private boolean isAfternoonShow;
    private List<SeatResponse> availableSeats;

    // Constructors
    public ShowResponse() {}

    public ShowResponse(Long id, String movieTitle, String theatreName, String screenName, 
                       String screenType, LocalDateTime showDateTime, BigDecimal basePrice, 
                       boolean isAfternoonShow) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.screenType = screenType;
        this.showDateTime = showDateTime;
        this.basePrice = basePrice;
        this.isAfternoonShow = isAfternoonShow;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAfternoonShow() {
        return isAfternoonShow;
    }

    public void setAfternoonShow(boolean afternoonShow) {
        isAfternoonShow = afternoonShow;
    }

    public List<SeatResponse> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatResponse> availableSeats) {
        this.availableSeats = availableSeats;
    }
}