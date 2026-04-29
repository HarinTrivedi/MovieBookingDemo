package com.demo.theatre.service;

import com.demo.theatre.data.Theatre;
import com.demo.theatre.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {
    private final TheatreRepository repository;

    public List<Theatre> getAllTheatres() {
        return repository.findAll();
    }

    public Theatre addTheatre(Theatre theatre) {
        return repository.save(theatre);
    }
}
