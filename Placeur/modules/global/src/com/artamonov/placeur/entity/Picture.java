package com.artamonov.placeur.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|path")
@Table(name = "PLACEUR_PICTURE")
@Entity(name = "placeur$Picture")
public class Picture extends StandardEntity {
    private static final long serialVersionUID = -2555936895429404573L;

    @Column(name = "PATH", nullable = false, unique = true)
    protected String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }


}