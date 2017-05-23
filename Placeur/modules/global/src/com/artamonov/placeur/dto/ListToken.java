/*
 * TODO Copyright
 */

package com.artamonov.placeur.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListToken {
    @SerializedName("list")
    List<MarkedPlaceDTO> list;
    @SerializedName("name")
    String name;

    public ListToken(List<MarkedPlaceDTO> list, String name) {
        this.list = list;
        this.name = name;
    }

    public List<MarkedPlaceDTO> getList() {
        return list;
    }

    public String getName() {
        return name;
    }
}
