package com.artamonov.placeurclient.service;

import com.artamonov.placeurclient.dto.AuthToken;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthService {

    @GET("/app/rest/v2/services/placeur_AuthorizationService/signin")
    Call<AuthToken> signIn(@Query("nickname") String nickname, @Query("password") String password);

    @GET("/app/rest/v2/services/placeur_AuthorizationService/signup")
    Call<AuthToken> signUp(@Query("nickname") String nickname, @Query("password") String password, @Query("cityId")UUID cityId);
}
