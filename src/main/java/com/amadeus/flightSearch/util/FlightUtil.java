package com.amadeus.flightSearch.util;

import com.amadeus.flightSearch.entity.Airport;
import com.amadeus.flightSearch.entity.Flight;
import com.amadeus.flightSearch.repo.AirportRepository;
import com.amadeus.flightSearch.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FlightUtil {
    private static final Random random = new Random();
    private final AirportService airportService;

    public List<Flight> generateFlightsForSameDay(LocalDateTime date, int numberOfFlights) {
        List<Flight> flights = new ArrayList<>();

        for (int i = 0; i < numberOfFlights; i++) {
            Flight flight = generateRandomFlight(date);
            flights.add(flight);
        }

        return flights;
    }

    @Scheduled(fixedRate = 5000)
    public Flight generateRandomFlight() {
        Flight flight = new Flight();

        List<Airport> airports = airportService.getAllAirports();
        Airport departureAirport = getRandomAirport(airports);
        Airport arrivalAirport;
        do {
            arrivalAirport = getRandomAirport(airports);
        } while (arrivalAirport.equals(departureAirport));

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureDateTime = now.plusDays(random.nextInt(30)).plusHours(random.nextInt(24));
        LocalDateTime returnDateTime = departureDateTime.plusDays(random.nextInt(10)).plusHours(random.nextInt(24));
        flight.setDepartureDate(departureDateTime.toLocalDate());
        flight.setReturnDate(returnDateTime.toLocalDate());
        flight.setDepartureDateTime(departureDateTime);
        flight.setReturnDateTime(returnDateTime);

        // Fiyatı rastgele seç (50 ile 1000 arasında)
        double price = 50 + (950 * random.nextDouble());
        price = Math.round(price * 100.0) / 100.0;
        flight.setPrice(price);
        return flight;
    }

    private static Airport getRandomAirport(List<Airport> airports) {
        return airports.get(random.nextInt(airports.size()));
    }

    private Flight generateRandomFlight(LocalDateTime date) {
        Flight flight = new Flight();

        // Kalkış ve varış havaalanlarını rastgele seç (farklı olmalı)
        List<Airport> airports = airportService.getAllAirports();
        Airport departureAirport = getRandomAirport(airports);
        Airport arrivalAirport;
        do {
            arrivalAirport = getRandomAirport(airports);
        } while (arrivalAirport.equals(departureAirport));

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        // Kalkış ve dönüş tarihini belirtilen tarih ve saatte yap
        LocalDateTime departureDateTime = date.plusHours(random.nextInt(24));
        LocalDateTime returnDateTime = departureDateTime.plusHours(random.nextInt(24));
        flight.setDepartureDateTime(departureDateTime);
        flight.setReturnDateTime(returnDateTime);
        flight.setDepartureDate(departureDateTime.toLocalDate());
        flight.setReturnDate(returnDateTime.toLocalDate());
        // Fiyatı rastgele seç (50 ile 1000 arasında)
        double price = 50 + (950 * random.nextDouble());
        price = Math.round(price * 100.0) / 100.0;

        flight.setPrice(price);


        return flight;
    }


}
