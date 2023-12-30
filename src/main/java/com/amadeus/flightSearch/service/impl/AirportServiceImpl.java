package com.amadeus.flightSearch.service.impl;

import com.amadeus.flightSearch.entity.Airport;
import com.amadeus.flightSearch.repo.AirportRepository;
import com.amadeus.flightSearch.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public Airport findByCity(String city) {
        var airport = airportRepository.findByCity(city);
        if (airport.isEmpty()){
            throw new RuntimeException("Airport bulunamadi");
        }
        return airport.get();
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport Save(Airport a) {
        return airportRepository.save(a);
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport airport) {
        Airport existingAirport = airportRepository.findById(id).orElse(null);
        if (existingAirport != null) {
            // Güncelleme işlemleri
            existingAirport.setCity(airport.getCity());
            return airportRepository.save(existingAirport);
        }
        return null;
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public void deleteAllAirports() {
        airportRepository.deleteAll();
    }
}
