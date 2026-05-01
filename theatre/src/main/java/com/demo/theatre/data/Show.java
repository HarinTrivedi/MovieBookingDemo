package com.demo.theatre.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Theatre theatre;
    @ManyToOne private Movie movie;
    private LocalDateTime dateTime;
    private int seatsAvailable;
    private LocalDateTime releaseDate;
}