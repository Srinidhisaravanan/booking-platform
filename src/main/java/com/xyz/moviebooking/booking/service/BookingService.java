package com.xyz.moviebooking.booking.service;

import com.xyz.moviebooking.booking.dto.BookingRequest;
import com.xyz.moviebooking.booking.dto.BookingResponse;
import com.xyz.moviebooking.booking.entity.Booking;
import com.xyz.moviebooking.booking.entity.BookingSeat;
import com.xyz.moviebooking.booking.repository.BookingRepository;
import com.xyz.moviebooking.movie.entity.Show;
import com.xyz.moviebooking.movie.entity.ShowSeat;
import com.xyz.moviebooking.movie.repository.ShowRepository;
import com.xyz.moviebooking.movie.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ShowRepository showRepository;
    
    @Autowired
    private ShowSeatRepository showSeatRepository;
    
    @Autowired
    private PricingService pricingService;

    public BookingResponse createBooking(BookingRequest request) {
        // 1. Validate show exists
        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));
        
        // 2. Validate seat availability
        List<ShowSeat> requestedSeats = showSeatRepository.findAvailableSeats(
            request.getShowId(), request.getSeatIds());
        
        if (requestedSeats.size() != request.getSeatIds().size()) {
            throw new RuntimeException("Some seats are not available");
        }
        
        // 3. Calculate pricing
        BigDecimal originalTotal = show.getBasePrice().multiply(BigDecimal.valueOf(request.getSeatIds().size()));
        BigDecimal finalTotal = pricingService.calculateTotalPrice(
            show.getBasePrice(), 
            request.getSeatIds().size(), 
            show.isAfternoonShow()
        );
        BigDecimal discount = originalTotal.subtract(finalTotal);
        
        // 4. Create booking
        Booking booking = new Booking();
        booking.setBookingReference(generateBookingReference());
        booking.setUserEmail(request.getUserEmail());
        booking.setUserPhone(request.getUserPhone());
        booking.setShow(show);
        booking.setTotalSeats(request.getSeatIds().size());
        booking.setTotalAmount(originalTotal);
        booking.setDiscountAmount(discount);
        booking.setFinalAmount(finalTotal);
        booking.confirmBooking();
        
        booking = bookingRepository.save(booking);
        
        // 5. Mark seats as booked
        requestedSeats.forEach(ShowSeat::bookSeat);
        showSeatRepository.saveAll(requestedSeats);
        
        return mapToBookingResponse(booking);
    }

    @Transactional(readOnly = true)
    public BookingResponse getBooking(String bookingReference) {
        Booking booking = bookingRepository.findByBookingReference(bookingReference)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingReference));
        
        return mapToBookingResponse(booking);
    }

    private String generateBookingReference() {
        String reference;
        do {
            reference = "BK" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        } while (bookingRepository.existsByBookingReference(reference));
        
        return reference;
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setBookingReference(booking.getBookingReference());
        response.setStatus(booking.getStatus().name());
        response.setTotalSeats(booking.getTotalSeats());
        response.setTotalAmount(booking.getTotalAmount());
        response.setDiscountAmount(booking.getDiscountAmount());
        response.setFinalAmount(booking.getFinalAmount());
        response.setBookingDateTime(booking.getBookingDateTime());
        response.setUserEmail(booking.getUserEmail());
        
        // Map show info
        Show show = booking.getShow();
        BookingResponse.ShowInfo showInfo = new BookingResponse.ShowInfo(
            show.getMovie().getTitle(),
            show.getTheatre().getName(),
            show.getScreen().getName(),
            show.getShowDateTime()
        );
        response.setShow(showInfo);
        
        // Map seat info
        List<BookingResponse.BookedSeatInfo> seatInfos = booking.getBookingSeats().stream()
                .map(bs -> new BookingResponse.BookedSeatInfo(
                    bs.getShowSeat().getSeat().getSeatIdentifier(),
                    bs.getShowSeat().getSeat().getSeatType(),
                    bs.getSeatPrice()
                ))
                .collect(Collectors.toList());
        response.setSeats(seatInfos);
        
        return response;
    }
}