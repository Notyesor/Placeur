package com.artamonov.placeur.dto;

import com.google.gson.annotations.SerializedName;

public class PublicRatingDTO {

    @SerializedName("ratingDTO")
    private RatingDTO ratingDTO;
    @SerializedName("user")
    private String user;

    public PublicRatingDTO(RatingDTO ratingDTO, String user) {
        this.ratingDTO = ratingDTO;
        this.user = user;
    }

    public RatingDTO getRatingDTO() {
        return ratingDTO;
    }

    public String getUser() {
        return user;
    }
}
