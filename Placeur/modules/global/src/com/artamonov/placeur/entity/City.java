package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|title")
@Table(name = "PLACEUR_CITY")
@Entity(name = "placeur$City")
public class City extends StandardEntity {
    private static final long serialVersionUID = 3610473268907906531L;

    @Column(name = "TITLE", nullable = false)
    protected String title;

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


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}