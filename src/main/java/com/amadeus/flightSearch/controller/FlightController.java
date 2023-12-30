package com.amadeus.flightSearch.controller;

import com.amadeus.flightSearch.dto.SearchRequest;
import com.amadeus.flightSearch.dto.SearchResponse;
import com.amadeus.flightSearch.entity.Airport;
import com.amadeus.flightSearch.entity.Flight;
import com.amadeus.flightSearch.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flights", description = "Flights management APIs")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date/{targetDate}")
    public ResponseEntity<List<Flight>> getFlightsByDate(@PathVariable String targetDate) {
        // targetDate formatı: yyyy-MM-ddTHH:mm:ss
        LocalDateTime dateTime = LocalDateTime.parse(targetDate);
        List<Flight> flights = flightService.getFlightsByDate(dateTime);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }


    @GetMapping("/search")
    public SearchResponse searchFlights(@RequestParam String departureAirport,
                                        @RequestParam String arrivalAirport,
                                        @RequestParam String departureDate,
                                        @RequestParam(required = false) String returnDate) {

        SearchRequest searchRequest = new SearchRequest(departureAirport, arrivalAirport, departureDate, returnDate);

        return flightService.searchFlights(searchRequest);
    }


    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllFlights() {
        flightService.deleteAllFlights();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
