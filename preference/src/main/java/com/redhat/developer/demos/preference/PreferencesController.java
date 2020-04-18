package com.redhat.developer.demos.preference;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferencesController {

    @GetMapping("/health/ready")
    @ResponseStatus(HttpStatus.OK)
    public void ready() {
    }

    @GetMapping("/health/live")
    @ResponseStatus(HttpStatus.OK)
    public void live() {
    }


    @GetMapping("/")
    public Object getPreferences() {
        return ResponseEntity.ok("Recieved GET request in preference service");

    }

}
