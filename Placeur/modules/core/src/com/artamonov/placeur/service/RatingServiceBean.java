package com.artamonov.placeur.service;

import org.springframework.stereotype.Service;

@Service(RatingService.NAME)
public class RatingServiceBean implements RatingService {

    @Override
    public String helloWorldMethod() {
        return "Hello, World!";
    }
}