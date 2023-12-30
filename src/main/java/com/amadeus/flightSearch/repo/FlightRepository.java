package com.amadeus.flightSearch.repo;

import com.amadeus.flightSearch.entity.Airport;
import com.amadeus.flightSearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE DATE(f.departureDateTime) = CURRENT_DATE")
    List<Flight> findFlightsDepartingToday();
    @Query("SELECT f FROM Flight f WHERE DATE(f.departureDateTime) = DATE(:targetDate)")
    List<Flight> findFlightsByDate(@Param("targetDate") LocalDateTime targetDate);

//    // Belirli bir tarihte kalkan uçuşları bulmak için özel bir sorgu
//    List<Flight> findFlightsByDate(LocalDateTime targetDate);

    // Fiyat aralığına göre uçuşları bulmak için özel bir sorgu
    List<Flight> findFlightsByPriceBetween(double minPrice, double maxPrice);

    // Örnek: Yeni bir uçuş eklemek için
    Flight save(Flight flight);

    // Örnek: Uçuşu güncellemek için
    Flight saveAndFlush(Flight flight);

    List<Flight> findByArrivalAirportAndDepartureAirportAndAndDepartureDate(Airport a, Airport b, LocalDate localDate);


}