package com.demo.theatre.controller;

import com.demo.theatre.data.Theatre;
import com.demo.theatre.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreRepository theatreRepo;

    @PostMapping
    public Theatre addTheatre(@RequestBody Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatre(@PathVariable Long id) {
        return theatreRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
