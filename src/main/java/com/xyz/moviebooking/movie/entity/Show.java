package com.xyz.moviebooking.movie.entity;

import com.xyz.moviebooking.shared.entity.BaseEntity;
import com.xyz.moviebooking.theatre.entity.Screen;
import com.xyz.moviebooking.theatre.entity.Theatre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show extends BaseEntity {

    @NotNull
    @Column(name = "show_date_time", nullable = false)
    private LocalDateTime showDateTime;

    @NotNull
    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowSeat> showSeats = new ArrayList<>();

    // Constructors
    public Show() {}

    public Show(LocalDateTime showDateTime, BigDecimal basePrice, Movie movie, Theatre theatre, Screen screen) {
        this.showDateTime = showDateTime;
        this.basePrice = basePrice;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
    }

    // Business Methods
    public boolean isAfternoonShow() {
        int hour = showDateTime.getHour();
        return hour >= 12 && hour < 17;
    }

    public boolean isShowActive() {
        return isActive && LocalDateTime.now().isBefore(showDateTime);
    }

    // Getters and Setters
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }
}