package com.artamonov.placeurclient.service;

import com.artamonov.placeurclient.dto.MarkedPlaceDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RatingService {

    @GET("/app/rest/v2/services/placeur_RatingService/getTopPlaces")
    Call<List<MarkedPlaceDTO>> getTopPlaces();
    @GET("/app/rest/v2/services/placeur_RatingService/makeRecommendation")
    Call<List<MarkedPlaceDTO>> makeRecommendation(@Query("id") String id);

}
