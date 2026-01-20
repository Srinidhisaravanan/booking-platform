package com.xyz.moviebooking.movie.repository;

import com.xyz.moviebooking.movie.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("SELECT s FROM Show s " +
           "JOIN FETCH s.movie m " +
           "JOIN FETCH s.theatre t " +
           "JOIN FETCH s.screen sc " +
           "WHERE m.id = :movieId " +
           "AND t.city = :city " +
           "AND DATE(s.showDateTime) = :date " +
           "AND s.isActive = true " +
           "AND m.isActive = true " +
           "AND t.isActive = true " +
           "ORDER BY s.showDateTime")
    List<Show> findShowsByMovieAndCityAndDate(
        @Param("movieId") Long movieId, 
        @Param("city") String city, 
        @Param("date") LocalDate date);

    @Query("SELECT s FROM Show s " +
           "JOIN FETCH s.movie m " +
           "JOIN FETCH s.theatre t " +
           "WHERE t.city = :city " +
           "AND DATE(s.showDateTime) = :date " +
           "AND s.isActive = true " +
           "ORDER BY m.title, s.showDateTime")
    List<Show> findShowsByCityAndDate(
        @Param("city") String city, 
        @Param("date") LocalDate date);
}