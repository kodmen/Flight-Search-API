package com.amadeus.flightSearch.service;

import com.amadeus.flightSearch.entity.Airport;

import java.util.List;

public interface AirportService {

    Airport findByCity(String city);

    List<Airport> getAllAirports();
    Airport Save(Airport a);

    Airport getAirportById(Long id);

    Airport createAirport(Airport airport);

    Airport updateAirport(Long id, Airport airport);

    void deleteAirport(Long id);

    void deleteAllAirports();
}