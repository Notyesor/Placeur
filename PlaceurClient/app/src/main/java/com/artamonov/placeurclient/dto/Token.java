package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    private String value;

    public Token() {

    }

    public String getValue() {
        return value;
    }
}
