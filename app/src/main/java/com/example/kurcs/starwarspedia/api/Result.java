package com.example.kurcs.starwarspedia.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by kurcs on 25.09.2017.
 */

public class Result extends RealmObject{ //klasa do obsługi zapytania

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("mass")
    @Expose
    public String mass;
    @SerializedName("hair_color")
    @Expose
    public String hairColor;
    @SerializedName("skin_color")
    @Expose
    public String skinColor;
    @SerializedName("eye_color")
    @Expose
    public String eyeColor;
    @SerializedName("birth_year")
    @Expose
    public String birthYear;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("homeworld")
    @Expose
    public String homeworld;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("edited")
    @Expose
    public String edited;
    @SerializedName("url")
    @Expose
    public String url;
    public boolean isFavourite;
}
