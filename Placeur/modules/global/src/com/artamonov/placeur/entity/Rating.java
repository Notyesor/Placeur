package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s|user,place")
@Table(name = "PLACEUR_RATING")
@Entity(name = "placeur$Rating")
public class Rating extends StandardEntity {
    private static final long serialVersionUID = -4131841160252500534L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLACE_ID")
    protected Place place;

    @Column(name = "MARK", nullable = false)
    protected Double mark;

    @Column(name = "IS_RECOMMENDED", nullable = false)
    protected Boolean isRecommended = false;

    @Column(name = "DESCRIPTION")
    protected String description;

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Double getMark() {
        return mark;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}