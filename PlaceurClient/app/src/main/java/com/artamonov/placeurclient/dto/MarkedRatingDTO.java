package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

public class MarkedRatingDTO {
    @SerializedName("ratingDTO")
    RatingDTO ratingDTO;
    @SerializedName("mark")
    Double mark;

    public MarkedRatingDTO(RatingDTO ratingDTO, Double mark) {
        this.ratingDTO = ratingDTO;
        this.mark = mark;
    }

    public RatingDTO getRatingDTO() {
        return ratingDTO;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
