package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingsToken {
    @SerializedName("ratings")
    private List<PublicRatingDTO> ratings;
    @SerializedName("name")
    private String name;

    public RatingsToken(List<PublicRatingDTO> ratings, String name) {
        this.ratings = ratings;
        this.name = name;
    }

    public List<PublicRatingDTO> getRatings() {
        return ratings;
    }

    public String getName() {
        return name;
    }
}
