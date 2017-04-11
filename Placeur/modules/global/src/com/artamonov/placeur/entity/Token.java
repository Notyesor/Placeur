package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "PLACEUR_TOKEN")
@Entity(name = "placeur$Token")
public class Token extends StandardEntity {
    private static final long serialVersionUID = -2568149289926882141L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", unique = true)
    protected User user;

    @Column(name = "TOKEN", nullable = false, unique = true)
    protected String token;

    public void setUser(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}