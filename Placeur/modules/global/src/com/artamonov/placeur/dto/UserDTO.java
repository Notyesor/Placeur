package com.artamonov.placeur.dto;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String nickname;
    private String mail;
    private String name;
    private String surname;
    private UUID city;
    private String password;

    public UserDTO(UUID id, String nickname, String mail, String name, String surname, UUID city, String password) {
        this.id = id;
        this.nickname = nickname;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getCity() {
        return city;
    }

    public void setCity(UUID city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
