package com.amadeus.flightSearch.dto;

import com.amadeus.flightSearch.entity.Flight;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {
    private List<Flight>  oneWayFlights;
    private List<Flight>  roundTripFlights;

    public static SearchResponse getInstance(List<Flight> oneWayFlights){
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setOneWayFlights(oneWayFlights);
        searchResponse.setRoundTripFlights(null);
        return searchResponse;
    }

    public static SearchResponse getInstance(List<Flight> oneWayFlights,List<Flight> roundTripFlights){
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setOneWayFlights(oneWayFlights);
        searchResponse.setRoundTripFlights(roundTripFlights);
        return searchResponse;
    }
}
