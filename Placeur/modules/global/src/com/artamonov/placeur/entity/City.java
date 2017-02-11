package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Table(name = "PLACEUR_CITY")
@Entity(name = "placeur$City")
public class City extends StandardEntity {
    private static final long serialVersionUID = 3610473268907906531L;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LOCATION_ID", unique = true)
    protected Location location;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }


}