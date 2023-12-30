package com.amadeus.flightSearch.service.impl;

import com.amadeus.flightSearch.dto.SearchRequest;
import com.amadeus.flightSearch.dto.SearchResponse;
import com.amadeus.flightSearch.entity.Airport;
import com.amadeus.flightSearch.entity.Flight;
import com.amadeus.flightSearch.repo.FlightRepository;
import com.amadeus.flightSearch.service.AirportService;
import com.amadeus.flightSearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public List<Flight> getFlightsByDate(LocalDateTime targetDate) {
        return flightRepository.findFlightsByDate(targetDate);
    }

    public List<Flight> getFlightsByPriceRange(double minPrice, double maxPrice) {
        return flightRepository.findFlightsByPriceBetween(minPrice, maxPrice);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> createAllFlight(List<Flight> flights) {
        return flightRepository.saveAll(flights);
    }

    public Flight updateFlight(Long id, Flight flight) {
        Flight existingFlight = flightRepository.findById(id).orElse(null);
        if (existingFlight != null) {
            // Güncelleme işlemleri
            existingFlight.setDepartureAirport(flight.getDepartureAirport());
            existingFlight.setArrivalAirport(flight.getArrivalAirport());
            existingFlight.setDepartureDateTime(flight.getDepartureDateTime());
            existingFlight.setReturnDateTime(flight.getReturnDateTime());
            existingFlight.setPrice(flight.getPrice());
            return flightRepository.saveAndFlush(existingFlight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

    public SearchResponse searchFlights(SearchRequest searchRequest) {

        if (Objects.isNull(searchRequest.getReturnDate())) {
            List<Flight> oneWay = oneWay(searchRequest);
            return SearchResponse.getInstance(oneWay);
        }

        List<Flight> oneWay = oneWay(searchRequest);
        List<Flight> returnWay = returnFlight(searchRequest);

        return SearchResponse.getInstance(oneWay,returnWay);
    }


    private List<Flight> oneWay(SearchRequest searchRequest) {
        Airport departureAirport = airportService.findByCity(searchRequest.getDepartureAirport());
        Airport arrivalAirport = airportService.findByCity(searchRequest.getArrivalAirport());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate departureDate = LocalDate.parse(searchRequest.getDepartureDate(), formatter);
        return flightRepository.findByArrivalAirportAndDepartureAirportAndAndDepartureDate(
                arrivalAirport, departureAirport, departureDate
        );
    }

    private List<Flight> returnFlight(SearchRequest searchRequest) {
        Airport departureAirport = airportService.findByCity(searchRequest.getDepartureAirport());
        Airport arrivalAirport = airportService.findByCity(searchRequest.getArrivalAirport());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate returnDate = LocalDate.parse(searchRequest.getReturnDate(), formatter);
        return flightRepository.findByArrivalAirportAndDepartureAirportAndAndDepartureDate(
                departureAirport, arrivalAirport, returnDate
        );
    }
}
