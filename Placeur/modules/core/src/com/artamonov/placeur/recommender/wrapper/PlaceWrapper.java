/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender.wrapper;

import com.artamonov.placeur.entity.City;
import com.artamonov.placeur.entity.Picture;
import com.artamonov.placeur.entity.Place;

import java.util.UUID;

/**
 * Created by Bacq on 21.02.2017.
 */
public class PlaceWrapper {
    private Place place;
    private double mark;

    public PlaceWrapper(Place place, double mark) {
        this.place = place;
        this.mark = mark;
    }

    public Double getLatitude() {
        return place.getLatitude();
    }

    public Double getLongitude() {
        return place.getLongitude();
    }

    public String getTitle() {
        return place.getTitle();
    }

    public String getDescription() {
        return place.getDescription();
    }

    public String getAddress() {
        return place.getAddress();
    }

    public City getCity() {
        return place.getCity();
    }

    public Picture getPicture() {
        return place.getPicture();
    }

    public UUID getId() {
        return place.getId();
    }

    public UUID getUuid() {
        return place.getUuid();
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "[" + place.getTitle() + " : " + mark + "]";
    }
}
