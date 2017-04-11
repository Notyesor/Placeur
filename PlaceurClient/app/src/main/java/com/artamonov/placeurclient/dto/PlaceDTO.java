package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class PlaceDTO {
    @SerializedName("id")
    private UUID id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("address")
    private String address;
    @SerializedName("cityId")
    private UUID cityId;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public PlaceDTO(UUID id, String title, String description, String address,
                    UUID cityId, Double latitude, Double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.cityId = cityId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
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
