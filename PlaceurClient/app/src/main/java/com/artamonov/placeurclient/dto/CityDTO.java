/*
 * TODO Copyright
 */

package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class CityDTO {

    @SerializedName("id")
    private UUID id;
    @SerializedName("title")
    private String title;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public CityDTO(UUID id, String title, Double latitude, Double longitude) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
