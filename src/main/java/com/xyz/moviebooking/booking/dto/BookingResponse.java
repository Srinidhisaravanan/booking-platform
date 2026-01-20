package com.xyz.moviebooking.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {
    private Long id;
    private String bookingReference;
    private String status;
    private Integer totalSeats;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private LocalDateTime bookingDateTime;
    private String userEmail;
    private ShowInfo show;
    private List<BookedSeatInfo> seats;

    // Constructors
    public BookingResponse() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ShowInfo getShow() {
        return show;
    }

    public void setShow(ShowInfo show) {
        this.show = show;
    }

    public List<BookedSeatInfo> getSeats() {
        return seats;
    }

    public void setSeats(List<BookedSeatInfo> seats) {
        this.seats = seats;
    }

    // Nested classes for better response structure
    public static class ShowInfo {
        private String movieTitle;
        private String theatreName;
        private String screenName;
        private LocalDateTime showDateTime;

        // Constructors, getters and setters
        public ShowInfo() {}

        public ShowInfo(String movieTitle, String theatreName, String screenName, LocalDateTime showDateTime) {
            this.movieTitle = movieTitle;
            this.theatreName = theatreName;
            this.screenName = screenName;
            this.showDateTime = showDateTime;
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

        public LocalDateTime getShowDateTime() {
            return showDateTime;
        }

        public void setShowDateTime(LocalDateTime showDateTime) {
            this.showDateTime = showDateTime;
        }
    }

    public static class BookedSeatInfo {
        private String seatIdentifier;
        private String seatType;
        private BigDecimal price;

        // Constructors, getters and setters
        public BookedSeatInfo() {}

        public BookedSeatInfo(String seatIdentifier, String seatType, BigDecimal price) {
            this.seatIdentifier = seatIdentifier;
            this.seatType = seatType;
            this.price = price;
        }

        public String getSeatIdentifier() {
            return seatIdentifier;
        }

        public void setSeatIdentifier(String seatIdentifier) {
            this.seatIdentifier = seatIdentifier;
        }

        public String getSeatType() {
            return seatType;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}