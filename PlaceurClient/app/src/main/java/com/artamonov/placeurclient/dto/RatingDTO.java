package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class RatingDTO {
    @SerializedName("id")
    private UUID id;
    @SerializedName("user")
    private UUID user;
    @SerializedName("place")
    private UUID place;
    @SerializedName("mark")
    private Double mark;
    @SerializedName("description")
    private String description;

    public RatingDTO(UUID id, UUID user, UUID place, Double mark, String description) {
        this.id = id;
        this.user = user;
        this.place = place;
        this.mark = mark;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public UUID getPlace() {
        return place;
    }

    public void setPlace(UUID place) {
        this.place = place;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
