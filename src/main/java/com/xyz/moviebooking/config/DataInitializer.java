package com.xyz.moviebooking.config;

import com.xyz.moviebooking.movie.entity.Movie;
import com.xyz.moviebooking.movie.entity.Show;
import com.xyz.moviebooking.movie.entity.ShowSeat;
import com.xyz.moviebooking.movie.repository.ShowRepository;
import com.xyz.moviebooking.movie.repository.ShowSeatRepository;
import com.xyz.moviebooking.theatre.entity.Screen;
import com.xyz.moviebooking.theatre.entity.Seat;
import com.xyz.moviebooking.theatre.entity.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create Movies
        Movie movie1 = new Movie("Spider-Man: No Way Home", "English", "Action", 148, "Jon Watts");
        movie1.setDescription("Spider-Man's identity is revealed and he must deal with the consequences");
        movie1.setReleaseDate(LocalDate.of(2025, 12, 15));
        entityManager.persist(movie1);

        Movie movie2 = new Movie("Avengers: Endgame", "English", "Action", 181, "Russo Brothers");
        movie2.setDescription("The Avengers assemble once more to reverse the damage caused by Thanos");
        movie2.setReleaseDate(LocalDate.of(2025, 4, 26));
        entityManager.persist(movie2);

        // Create Theatres
        Theatre theatre1 = new Theatre("PVR Phoenix Mall", "High Street Phoenix, Lower Parel", "Mumbai");
        theatre1.setPhoneNumber("+91-22-1234-5678");
        entityManager.persist(theatre1);

        Theatre theatre2 = new Theatre("INOX R-City Mall", "R-City Mall, Ghatkopar", "Mumbai");
        theatre2.setPhoneNumber("+91-22-8765-4321");
        entityManager.persist(theatre2);

        // Create Screens
        Screen screen1 = new Screen("Screen 1", "IMAX", 100, theatre1);
        theatre1.getScreens().add(screen1);
        entityManager.persist(screen1);

        Screen screen2 = new Screen("Screen 2", "Dolby Atmos", 80, theatre1);
        theatre1.getScreens().add(screen2);
        entityManager.persist(screen2);

        Screen screen3 = new Screen("Screen 1", "Regular", 120, theatre2);
        theatre2.getScreens().add(screen3);
        entityManager.persist(screen3);

        // Create Seats for Screen 1 (IMAX)
        createSeatsForScreen(screen1, 10, 10, BigDecimal.valueOf(350)); // Premium pricing for IMAX

        // Create Seats for Screen 2 (Dolby Atmos)
        createSeatsForScreen(screen2, 8, 10, BigDecimal.valueOf(300)); // Premium pricing for Dolby

        // Create Seats for Screen 3 (Regular)
        createSeatsForScreen(screen3, 12, 10, BigDecimal.valueOf(200)); // Regular pricing

        // Create Shows
        LocalDateTime today = LocalDateTime.now().toLocalDate().atTime(10, 0);
        
        // Morning shows
        Show show1 = new Show(today.withHour(10).withMinute(30), BigDecimal.valueOf(350), movie1, theatre1, screen1);
        entityManager.persist(show1);

        Show show2 = new Show(today.withHour(14).withMinute(0), BigDecimal.valueOf(350), movie1, theatre1, screen1); // Afternoon
        entityManager.persist(show2);

        Show show3 = new Show(today.withHour(18).withMinute(30), BigDecimal.valueOf(350), movie1, theatre1, screen1);
        entityManager.persist(show3);

        Show show4 = new Show(today.withHour(13).withMinute(30), BigDecimal.valueOf(300), movie2, theatre1, screen2); // Afternoon
        entityManager.persist(show4);

        Show show5 = new Show(today.withHour(19).withMinute(0), BigDecimal.valueOf(300), movie2, theatre1, screen2);
        entityManager.persist(show5);

        Show show6 = new Show(today.withHour(15).withMinute(0), BigDecimal.valueOf(200), movie1, theatre2, screen3); // Afternoon
        entityManager.persist(show6);

        // Flush to ensure shows are persisted before creating show seats
        entityManager.flush();

        // Create ShowSeats for all shows
        createShowSeatsForShow(show1);
        createShowSeatsForShow(show2);
        createShowSeatsForShow(show3);
        createShowSeatsForShow(show4);
        createShowSeatsForShow(show5);
        createShowSeatsForShow(show6);

        System.out.println("Sample data initialized successfully!");
        System.out.println("Available APIs:");
        System.out.println("1. GET /api/v1/shows?city=Mumbai&date=2026-01-20 - Browse all shows");
        System.out.println("2. GET /api/v1/shows?movieId=1&city=Mumbai&date=2026-01-20 - Browse shows by movie");
        System.out.println("3. GET /api/v1/shows/1/seats - View seats for show");
        System.out.println("4. POST /api/v1/bookings - Create booking");
        System.out.println("5. GET /api/v1/bookings/{bookingReference} - Get booking details");
    }

    private void createSeatsForScreen(Screen screen, int rows, int seatsPerRow, BigDecimal basePrice) {
        List<Seat> seats = new ArrayList<>();
        
        for (int row = 1; row <= rows; row++) {
            char rowChar = (char) ('A' + row - 1);
            String rowNumber = String.valueOf(rowChar);
            
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatType = (row <= 3) ? "Premium" : "Regular";
                BigDecimal seatPrice = (row <= 3) ? basePrice.multiply(BigDecimal.valueOf(1.5)) : basePrice;
                
                Seat seat = new Seat(rowNumber, seatNum, seatType, seatPrice, screen);
                seats.add(seat);
                entityManager.persist(seat);
            }
        }
        screen.setSeats(seats);
    }

    private void createShowSeatsForShow(Show show) {
        List<Seat> seats = show.getScreen().getSeats();
        List<ShowSeat> showSeats = new ArrayList<>();
        
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat(show, seat, seat.getBasePrice());
            showSeats.add(showSeat);
            entityManager.persist(showSeat);
        }
        
        show.setShowSeats(showSeats);
    }
}