package com.xyz.moviebooking.movie.controller;

import com.xyz.moviebooking.movie.dto.ShowResponse;
import com.xyz.moviebooking.movie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    /**
     * Browse shows by movie, city and date - READ SCENARIO
     * Example: GET /shows?movieId=1&city=Mumbai&date=2026-01-25
     */
    @GetMapping
    public ResponseEntity<List<ShowResponse>> getShows(
            @RequestParam(required = false) Long movieId,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        List<ShowResponse> shows;
        if (movieId != null) {
            shows = showService.findShowsByMovieAndCity(movieId, city, date);
        } else {
            shows = showService.findShowsByCity(city, date);
        }
        
        return ResponseEntity.ok(shows);
    }

    /**
     * Get show details with seat availability
     * Example: GET /shows/1/seats
     */
    @GetMapping("/{showId}/seats")
    public ResponseEntity<ShowResponse> getShowWithSeats(@PathVariable Long showId) {
        ShowResponse show = showService.getShowWithSeats(showId);
        return ResponseEntity.ok(show);
    }
}