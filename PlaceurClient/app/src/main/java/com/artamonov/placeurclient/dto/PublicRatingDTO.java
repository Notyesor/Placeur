package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

public class PublicRatingDTO {

    @SerializedName("ratingDTO")
    RatingDTO ratingDTO;
    @SerializedName("user")
    String user;

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
