package com.demo.theatre.service;

import com.demo.theatre.data.Movie;
import com.demo.theatre.data.Show;
import com.demo.theatre.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository repository;

    public Show save(Show show) {
        return repository.save(show);
    }

    public List<Show> findAll() { return repository.findAll(); }

    public Optional<Show> findById(Long id) { return repository.findById(id); }

    public List<Show> findShowsByTheatreAndMovie(String theatreName, String title) {
        return repository.findByTheatre_NameIgnoreCaseAndMovie_TitleIgnoreCase(theatreName, title);
    }

    public List<Show> findShowsInDateRange(LocalDateTime toDate, LocalDateTime fromDate) {
        return repository.findByReleaseDateBeforeAndReleaseDateAfter(toDate, fromDate);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
