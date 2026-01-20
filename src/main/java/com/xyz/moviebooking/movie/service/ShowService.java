package com.xyz.moviebooking.movie.service;

import com.xyz.moviebooking.movie.dto.SeatResponse;
import com.xyz.moviebooking.movie.dto.ShowResponse;
import com.xyz.moviebooking.movie.entity.Show;
import com.xyz.moviebooking.movie.entity.ShowSeat;
import com.xyz.moviebooking.movie.repository.ShowRepository;
import com.xyz.moviebooking.movie.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public List<ShowResponse> findShowsByMovieAndCity(Long movieId, String city, LocalDate date) {
        List<Show> shows = showRepository.findShowsByMovieAndCityAndDate(movieId, city, date);
        return shows.stream()
                .map(this::mapToShowResponse)
                .collect(Collectors.toList());
    }

    public List<ShowResponse> findShowsByCity(String city, LocalDate date) {
        List<Show> shows = showRepository.findShowsByCityAndDate(city, date);
        return shows.stream()
                .map(this::mapToShowResponse)
                .collect(Collectors.toList());
    }

    public ShowResponse getShowWithSeats(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found: " + showId));
        
        List<ShowSeat> showSeats = showSeatRepository.findByShowIdWithSeats(showId);
        
        ShowResponse response = mapToShowResponse(show);
        response.setAvailableSeats(mapToSeatResponses(showSeats));
        
        return response;
    }

    private ShowResponse mapToShowResponse(Show show) {
        return new ShowResponse(
            show.getId(),
            show.getMovie().getTitle(),
            show.getTheatre().getName(),
            show.getScreen().getName(),
            show.getScreen().getScreenType(),
            show.getShowDateTime(),
            show.getBasePrice(),
            show.isAfternoonShow()
        );
    }

    private List<SeatResponse> mapToSeatResponses(List<ShowSeat> showSeats) {
        return showSeats.stream()
                .map(ss -> new SeatResponse(
                    ss.getId(),
                    ss.getSeat().getRowNumber(),
                    ss.getSeat().getSeatNumber(),
                    ss.getSeat().getSeatType(),
                    ss.getFinalPrice(),
                    ss.getStatus().name()
                ))
                .collect(Collectors.toList());
    }
}