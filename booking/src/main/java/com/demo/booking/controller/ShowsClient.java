package com.demo.booking.controller;

import com.demo.booking.controller.dto.ShowDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "theatre-service")
public interface ShowsClient {

    @GetMapping("/shows/{id}")
    ShowDTO getShowById(@PathVariable("id") Long id);
}
