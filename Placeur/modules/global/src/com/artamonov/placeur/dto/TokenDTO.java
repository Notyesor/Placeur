package com.artamonov.placeur.dto;

import java.util.UUID;

public class TokenDTO {

    private UUID id;
    private UUID user;
    private String value;

    public TokenDTO(UUID id, UUID user, String value) {
        this.id = id;
        this.user = user;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
