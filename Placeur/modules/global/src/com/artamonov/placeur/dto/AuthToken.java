package com.artamonov.placeur.dto;

import com.google.gson.annotations.SerializedName;

public class AuthToken {
    @SerializedName("user")
    private UserDTO user;
    @SerializedName("name")
    private String name;

    public AuthToken(UserDTO user, String name) {
        this.user = user;
        this.name = name;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getName() {
        return name;
    }
}
