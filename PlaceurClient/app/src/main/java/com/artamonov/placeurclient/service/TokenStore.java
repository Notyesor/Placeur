package com.artamonov.placeurclient.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.artamonov.placeurclient.dto.TokenDTO;

import java.util.UUID;

public class TokenStore {

    public static void setToken(Context context, TokenDTO token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token.getValue());
        editor.putString("id", token.getId().toString());
        editor.putString("userId", token.getUser().toString());
        editor.apply();
    }

    public static TokenDTO getToken(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tokenValue = sharedPreferences.getString("token", null);
        String idString = sharedPreferences.getString("id", null);
        String userIdString = sharedPreferences.getString("userId", null);
        return tokenValue != null ? new TokenDTO(UUID.fromString(idString), UUID.fromString(userIdString), tokenValue) : null;
    }
}
