package com.demo.theatre.controller;

import com.demo.theatre.data.Show;
import com.demo.theatre.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private ShowService showService;

    @GetMapping
    public List<Show> getAllShows() {
        return showService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShow(@PathVariable Long id) {
        return showService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Show addShow(@RequestBody Show show) {
        return showService.save(show);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show updated) {
        return showService.findById(id).map(show -> {
            show.setDateTime(updated.getDateTime());
            show.setSeatsAvailable(updated.getSeatsAvailable());
            return ResponseEntity.ok(showService.save(show));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Show> filterBySearchParams(
            @RequestParam("theatre") String theatre,
            @RequestParam("title") String title,
            @RequestParam("releaseDate") String fromDate,
            @RequestParam("releaseDate") String toDate) {

        List<Show> list;
        if(!theatre.isEmpty() && !title.isEmpty())
            list = showService.findShowsByTheatreAndMovie(theatre, title);
        else if(!fromDate.isEmpty() && !toDate.isEmpty()) {
            var dateBefore = LocalDateTime.now();
            try {
                dateBefore = LocalDateTime.parse(toDate);
            } catch (DateTimeParseException e) {
                System.out.println("Error. fallback to today's date");
            }
            var dateAfter = LocalDateTime.now();
            try {
                dateAfter = LocalDateTime.parse(fromDate);
            } catch (DateTimeParseException e) {
                System.out.println("Error. fallback to today's date");
            }
            list = showService.findShowsInDateRange(dateBefore, dateAfter);
        }
        else
            list = showService.findAll();

        return list;
    }
}
