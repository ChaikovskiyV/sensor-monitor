package com.example.sensormonitor.controller;

import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import com.example.sensormonitor.security.jwt.JwtAuthRequest;
import com.example.sensormonitor.security.jwt.JwtAuthResponse;
import com.example.sensormonitor.security.jwt.JwtTokenProvider;
import com.example.sensormonitor.security.jwt.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.sensormonitor.exception.ErrorMessages.NOT_CORRECT_LOGIN_DATA;

@RestController
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth/login")
    public HttpEntity<JwtAuthResponse> login(@Valid @RequestBody JwtAuthRequest request, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                throw new ApplicationNotValidDataException(NOT_CORRECT_LOGIN_DATA, bindingResult);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();

            String accessToken = jwtTokenProvider.generateToken(userDetails);
            JwtAuthResponse authResponse = new JwtAuthResponse(userDetails.getUsername(), accessToken, userDetails.getAuthorities());

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}