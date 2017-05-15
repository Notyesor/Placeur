package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s []|nickname")
@Table(name = "PLACEUR_USER")
@Entity(name = "placeur$User")
public class User extends StandardEntity {
    private static final long serialVersionUID = -4027079533558603751L;

    @Column(name = "NICKNAME", nullable = false, unique = true, length = 20)
    protected String nickname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_ID")
    protected City city;


    @Column(name = "PASSWORD", nullable = false)
    protected String password;

    @Column(name = "SIMILARITY", nullable = false)
    protected Integer similarity;

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public Integer getSimilarity() {
        return similarity;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }


}