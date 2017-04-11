package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class TokenDTO {

    @SerializedName("id")
    private UUID id;
    @SerializedName("user")
    private UUID user;
    @SerializedName("value")
    private String value;

    public TokenDTO(UUID id, UUID user, String value) {
        this.id = id;
        this.user = user;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
