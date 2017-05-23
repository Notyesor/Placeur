package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class UserDTO {
    @SerializedName("id")
    private UUID id;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("city")
    private UUID city;
    @SerializedName("password")
    private String password;
    @SerializedName("similarity")
    private Integer similarity;

    public UserDTO(UUID id, String nickname, UUID city, String password, Integer similarity) {
        this.id = id;
        this.nickname = nickname;
        this.city = city;
        this.password = password;
        this.similarity = similarity;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UUID getCity() {
        return city;
    }

    public void setCity(UUID city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }
}
