package com.xyz.moviebooking.booking.entity;

import com.xyz.moviebooking.shared.entity.BaseEntity;
import com.xyz.moviebooking.movie.entity.ShowSeat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_seats")
public class BookingSeat extends BaseEntity {

    @NotNull
    @Column(name = "seat_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal seatPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_seat_id", nullable = false)
    private ShowSeat showSeat;

    // Constructors
    public BookingSeat() {}

    public BookingSeat(Booking booking, ShowSeat showSeat, BigDecimal seatPrice) {
        this.booking = booking;
        this.showSeat = showSeat;
        this.seatPrice = seatPrice;
    }

    // Getters and Setters
    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public ShowSeat getShowSeat() {
        return showSeat;
    }

    public void setShowSeat(ShowSeat showSeat) {
        this.showSeat = showSeat;
    }
}