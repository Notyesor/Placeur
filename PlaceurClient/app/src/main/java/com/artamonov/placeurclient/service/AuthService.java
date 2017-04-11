package com.artamonov.placeurclient.service;

import com.artamonov.placeurclient.dto.Token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthService {

    @GET("/app/rest/v2/services/placeur_AuthorizationService/signin")
    Call<Token> signIn(@Query("nickname") String nickname, @Query("password") String password);
}
