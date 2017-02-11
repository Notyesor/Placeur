package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "PLACEUR_LOCATION")
@Entity(name = "placeur$Location")
public class Location extends StandardEntity {
    private static final long serialVersionUID = -948627112058713008L;

    @Column(name = "LATITUDE", nullable = false)
    protected Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    protected Double longitude;

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }


}