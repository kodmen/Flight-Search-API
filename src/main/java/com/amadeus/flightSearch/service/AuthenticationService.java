package com.amadeus.flightSearch.service;

import com.amadeus.flightSearch.dto.AuthenticationRequest;
import com.amadeus.flightSearch.dto.AuthenticationResponse;
import com.amadeus.flightSearch.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
