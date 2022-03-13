package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

public class TransferService {
    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;
    private String authToken = null;

    public TransferService(){
    }
//    public TransferService(AuthenticatedUser authenticatedUser){
//        this.currentUser = authenticatedUser;
//    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public Transfer[] getTransfersFromUserId(AuthenticatedUser authenticatedUser) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.exchange(API_BASE_URL + "account/transfers/" + authenticatedUser.getUser().getId(),
                    HttpMethod.GET,
                    makeAuthEntity(authenticatedUser),
                    Transfer[].class).getBody();
//            transfers = response.getBody();
        } catch(RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    public Transfer[] viewPendingTransfers(AuthenticatedUser authenticatedUser) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.exchange(API_BASE_URL + "transfers/" + authenticatedUser.getUser().getId(),
                    HttpMethod.GET,
                    makeAuthEntity(authenticatedUser),
                    Transfer[].class
            ).getBody();
        } catch(RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }



    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser authenticatedUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }




}
