package com.amadeus.flightSearch;

import com.amadeus.flightSearch.entity.Flight;
import com.amadeus.flightSearch.service.FlightService;
import com.amadeus.flightSearch.util.FlightUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class FlightSearchApplication{

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApplication.class, args);
	}

}
