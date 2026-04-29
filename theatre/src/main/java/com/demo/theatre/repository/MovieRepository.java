package com.demo.theatre.repository;

import com.demo.theatre.data.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByReleaseDateBefore(LocalDateTime releaseDate, Pageable pageable);
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Movie> findByCityIgnoreCase(String city, Pageable pageable);
    Page<Movie> findByGenreIgnoreCase(String genre, Pageable pageable);
    Page<Movie> findByLanguageIgnoreCase(String language, Pageable pageable);
}