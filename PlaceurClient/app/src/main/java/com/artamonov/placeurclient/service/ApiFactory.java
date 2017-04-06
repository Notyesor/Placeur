package com.artamonov.placeurclient.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;

    private static final OkHttpClient CLIENT;

    static {
        CLIENT =new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
    }

    @NonNull
    public static AuthService getAuthService() {
        return getRetrofit().create(AuthService.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(CLIENT)
                .build();
    }

    private class BuildConfig {
        private static final String API_ENDPOINT = "http://192.168.43.92:8080";
    }
}
