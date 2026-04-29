package com.demo.theatre.repository;

import com.demo.theatre.data.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByReleaseDateBeforeAndReleaseDateAfter(LocalDateTime before, LocalDateTime after);

    // Derived query using nested entity fields
    List<Show> findByTheatre_NameIgnoreCaseAndMovie_TitleIgnoreCase(String theatreName, String movieTitle);
}
