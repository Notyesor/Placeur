package com.artamonov.placeurclient.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.artamonov.placeurclient.dto.UserDTO;

import java.util.UUID;

public class Store {

    public static UserDTO getUser(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String idString = sharedPreferences.getString("id", null);
        String nickname = sharedPreferences.getString("nickname", null);
        String password = sharedPreferences.getString("password", null);
        String city = sharedPreferences.getString("cityId", null);
        String similarity = sharedPreferences.getString("similarity", null);
        return idString != null ? new UserDTO(UUID.fromString(idString),
                nickname,
                UUID.fromString(city),
                password,
                Integer.parseInt(similarity)
        ) : null;
    }

    public static void setUser(UserDTO user, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", user.getId().toString());
        editor.putString("nickname", user.getNickname());
        editor.putString("password", user.getPassword());
        editor.putString("cityId", user.getCity().toString());
        editor.putString("similarity", user.getSimilarity().toString());
        editor.apply();
    }
}
