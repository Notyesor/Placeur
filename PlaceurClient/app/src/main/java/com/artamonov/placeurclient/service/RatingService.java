package com.artamonov.placeurclient.service;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bacq on 13.02.2017.
 */

public interface RatingService {

    @GET("")
    Callback<String> getHelloWorld();
}
