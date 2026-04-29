package com.demo.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "theatre-service")
public interface ShowsClient {

    @GetMapping("/shows/{id}")
    ShowDTO getShowById(@PathVariable("id") Long id);
}
