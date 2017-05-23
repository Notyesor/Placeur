package com.artamonov.placeurclient.service;

import com.artamonov.placeurclient.dto.ListToken;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RatingService {

    @GET("/app/rest/v2/services/placeur_RatingService/getTopPlaces")
    Call<ListToken> getTopPlaces();
    @GET("/app/rest/v2/services/placeur_RatingService/getRecommendations")
    Call<ListToken> getRecommendations(@Query("id") String id);

}
