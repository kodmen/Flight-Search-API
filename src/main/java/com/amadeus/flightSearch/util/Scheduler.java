package com.amadeus.flightSearch.util;

import com.amadeus.flightSearch.entity.Flight;
import com.amadeus.flightSearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final FlightUtil flightUtil;
    private final FlightService flightService;

    @Scheduled(cron = "0 0 0 * * *") // "0 0 0 * * *" her gün 00:00:00'da çalışır
    public void scheduleTask() {

        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = flightUtil.generateFlightsForSameDay(now,100);
        flightService.createAllFlight(flights);
    }
}
