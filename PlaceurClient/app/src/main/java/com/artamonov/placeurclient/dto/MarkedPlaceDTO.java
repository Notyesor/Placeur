package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

public class MarkedPlaceDTO {
    @SerializedName("placeDTO")
    private PlaceDTO placeDTO;
    @SerializedName("mark")
    private Double mark;

    public MarkedPlaceDTO(PlaceDTO placeDTO, Double mark) {
        this.placeDTO = placeDTO;
        this.mark = mark;
    }

    public PlaceDTO getPlaceDTO() {
        return placeDTO;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
