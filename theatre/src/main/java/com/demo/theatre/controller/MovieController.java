package com.demo.theatre.controller;

import com.demo.theatre.data.Movie;
import com.demo.theatre.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updated) {
        return movieService.findById(id).map(movie -> {
            movie.setTitle(updated.getTitle());
            movie.setLanguage(updated.getLanguage());
            movie.setGenre(updated.getGenre());
            movie.setDuration(updated.getDuration());
            return ResponseEntity.ok(movieService.save(movie));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Page<Movie> filterBySearchParams(
            @RequestParam("title") String title,
            @RequestParam("city") String city,
            @RequestParam("genre") String genre,
            @RequestParam("language") String language,
            @RequestParam("releaseDate") String fromDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        //sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Page<Movie> list;
        if(!title.isEmpty())
            list = movieService.filterByTitle(title, pageable);
        else if(!city.isEmpty())
            list = movieService.filterByCity(city, pageable);
        else if(!genre.isEmpty())
            list = movieService.filterByGenre(genre, pageable);
        else if(!language.isEmpty())
            list = movieService.filterByLanguage(language, pageable);
        else if(!fromDate.isEmpty()) {
            var date = LocalDateTime.now();
            try {
                date = LocalDateTime.parse(fromDate);
            } catch (DateTimeParseException e) {
                System.out.println("Error. fallback to today's date");
            }
            list = movieService.findByReleaseDateBefore(date, pageable);
        }
        else
            list = movieService.findAll(pageable);

        return list;
    }
}

