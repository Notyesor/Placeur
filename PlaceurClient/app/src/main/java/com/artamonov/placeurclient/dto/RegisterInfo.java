package com.artamonov.placeurclient.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class RegisterInfo {
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("mail")
    private String mail;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("cityId")
    private UUID cityId;

    public RegisterInfo(String nickname, String mail, String password, String name, String surname, UUID cityId) {
        this.nickname = nickname;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cityId = cityId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public UUID getCityId() {
        return cityId;
    }
}
