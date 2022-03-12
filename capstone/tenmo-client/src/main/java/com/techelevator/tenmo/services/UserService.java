package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.util.BasicLogger;
import io.cucumber.java.sl.In;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class UserService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;
    private String authToken = null;


    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public BigDecimal getBalance(AuthenticatedUser authenticatedUser) {
        BigDecimal balance = null;

        try {
            balance = restTemplate.exchange(API_BASE_URL + "/balance/" + authenticatedUser.getUser().getId(),
                    HttpMethod.GET, makeAuthEntity(authenticatedUser), BigDecimal.class).getBody();
        }
        catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }


    public User[] listUsers(AuthenticatedUser authenticatedUser) {
        User[] users = null;
        try {
            ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "user/",
                    HttpMethod.GET, makeAuthEntity(authenticatedUser), User[].class);
            users = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }




    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser authenticatedUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }


}
