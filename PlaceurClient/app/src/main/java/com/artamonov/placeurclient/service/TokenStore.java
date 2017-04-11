package com.artamonov.placeurclient.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.artamonov.placeurclient.dto.Token;

public class TokenStore {

    public static void setToken(Context context, Token token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token.getValue());
        editor.apply();
    }

    public static Token getToken(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tokenValue = sharedPreferences.getString("token", null);
        return tokenValue != null ? new Token(tokenValue) : null;
    }
}
