/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender.wrapper;

import com.artamonov.placeur.entity.Place;
import com.artamonov.placeur.entity.Rating;
import com.artamonov.placeur.entity.User;

import java.util.UUID;

public class RatingWrapper {
    private Rating rating;
    private double mark;

    public RatingWrapper(Rating rating, double mark) {
        this.rating = rating;
        this.mark = mark * rating.getMark();
    }

    public User getUser() {
        return rating.getUser();
    }

    public Place getPlace() {
        return rating.getPlace();
    }

    public Double getMark() {
        return mark;
    }

    public String getDescription() {
        return rating.getDescription();
    }

    public UUID getId() {
        return rating.getId();
    }

    public UUID getUuid() {
        return rating.getUuid();
    }

    @Override
    public String toString() {
        return "User: " + rating.getUser().getName() + ", Place: " + rating.getPlace().getTitle() + ", Mark: " + mark;
    }
}
