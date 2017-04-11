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

@NamePattern("%s [%s]|nickname,mail")
@Table(name = "PLACEUR_USER")
@Entity(name = "placeur$User")
public class User extends StandardEntity {
    private static final long serialVersionUID = -4027079533558603751L;

    @Column(name = "NICKNAME", nullable = false, unique = true, length = 20)
    protected String nickname;

    @Column(name = "MAIL", nullable = false, unique = true)
    protected String mail;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "SURNAME", length = 50)
    protected String surname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_ID")
    protected City city;


    @Column(name = "PASSWORD", nullable = false)
    protected String password;

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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }


}