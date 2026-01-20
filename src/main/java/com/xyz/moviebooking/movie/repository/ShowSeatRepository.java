package com.xyz.moviebooking.movie.repository;

import com.xyz.moviebooking.movie.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Query("SELECT ss FROM ShowSeat ss " +
           "JOIN FETCH ss.seat s " +
           "WHERE ss.show.id = :showId " +
           "ORDER BY s.rowNumber, s.seatNumber")
    List<ShowSeat> findByShowIdWithSeats(@Param("showId") Long showId);

    @Query("SELECT ss FROM ShowSeat ss " +
           "WHERE ss.show.id = :showId " +
           "AND ss.id IN :seatIds " +
           "AND ss.status = 'AVAILABLE'")
    List<ShowSeat> findAvailableSeats(@Param("showId") Long showId, @Param("seatIds") List<Long> seatIds);

    @Query("SELECT ss FROM ShowSeat ss " +
           "WHERE ss.show.id = :showId " +
           "AND ss.id IN :seatIds")
    List<ShowSeat> findByShowIdAndSeatIds(@Param("showId") Long showId, @Param("seatIds") List<Long> seatIds);
}