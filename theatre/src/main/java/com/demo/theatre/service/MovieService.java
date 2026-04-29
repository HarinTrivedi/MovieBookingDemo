package com.demo.theatre.service;

import com.demo.theatre.data.Movie;
import com.demo.theatre.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;

    public Page<Movie> findAll(Pageable pageable) { return repository.findAll(pageable); }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Movie> filterByTitle(String title, Pageable pageable) {
        return repository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Movie> filterByCity(String city, Pageable pageable) {
        return repository.findByCityIgnoreCase(city, pageable);
    }

    public Page<Movie> filterByGenre(String genre, Pageable pageable) {
        return repository.findByGenreIgnoreCase(genre, pageable);
    }

    public Page<Movie> filterByLanguage(String language, Pageable pageable) {
        return repository.findByLanguageIgnoreCase(language, pageable);
    }

    public Page<Movie> findByReleaseDateBefore(LocalDateTime date, Pageable pageable) {
        return repository.findByReleaseDateBefore(date, pageable);
    }
}
