package com.example.countrismusic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("name")
    public Name name;

    @SerializedName("continents")
    public List<String> continents;

    @SerializedName("flags")
    public Flags flags;

    public static class Name {
        @SerializedName("common")
        public String common;
    }

    public static class Flags {
        @SerializedName("png")
        public String png;
    }
}
