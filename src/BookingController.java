
package com.xyz.moviebooking.booking.controller;

import com.xyz.moviebooking.booking.dto.BookingRequest;
import com.xyz.moviebooking.booking.dto.BookingResponse;
import com.xyz.moviebooking.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{bookingReference}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable String bookingReference) {
        BookingResponse response = bookingService.getBooking(bookingReference);
        return ResponseEntity.ok(response);
    }
}
