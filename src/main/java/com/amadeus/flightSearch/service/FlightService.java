package com.amadeus.flightSearch.service;

import com.amadeus.flightSearch.dto.SearchRequest;
import com.amadeus.flightSearch.dto.SearchResponse;
import com.amadeus.flightSearch.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlightById(Long id);

    List<Flight> getFlightsByDate(LocalDateTime targetDate);

    Flight createFlight(Flight flight);
    List<Flight> createAllFlight(List<Flight> flights);

    Flight updateFlight(Long id, Flight flight);

    void deleteFlight(Long id);

    void deleteAllFlights();

    SearchResponse searchFlights(SearchRequest searchRequest);
}
