package com.artamonov.placeur.service;


import com.artamonov.placeur.entity.User;

import java.util.Map;

public interface RatingService {
    String NAME = "placeur_RatingService";
    String makeRecommendation(String id);
}