package com.amadeus.flightSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amadeus.*")
public class FlightSearchApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/springboot3");
		SpringApplication.run(FlightSearchApplication.class, args);
	}

}
