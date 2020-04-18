package com.redhat.developer.demos.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    private static final String RESPONSE_STRING_FORMAT = "Recieved a GET Request in customer service => %s\n";


    @Autowired
    RestTemplate restTemplate;

    private final String remoteURL = "http://preference:8080";

    @GetMapping("/health/ready")
    @ResponseStatus(HttpStatus.OK)
    public void ready() {
    }

    @GetMapping("/health/live")
    @ResponseStatus(HttpStatus.OK)
    public void live() {
    }

    
    @GetMapping
    public Object getCustomer() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(remoteURL, String.class);
        final String response = responseEntity.getBody();
        return ResponseEntity.ok(String.format(RESPONSE_STRING_FORMAT, response.trim()));
    }


}
