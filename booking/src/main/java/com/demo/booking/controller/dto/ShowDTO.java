package com.demo.booking.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO {
    private int id;
    private String movieName;
    private LocalDateTime dateTime;
    private int seatsAvailable;
}
