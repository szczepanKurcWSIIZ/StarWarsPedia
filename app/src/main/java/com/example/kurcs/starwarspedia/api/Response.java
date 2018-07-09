package com.example.kurcs.starwarspedia.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kurcs on 25.09.2017.
 */

public class Response { // klasa do obsługi zapytania

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("next")
    @Expose
    public String next;
    @SerializedName("previous")
    @Expose
    public Object previous;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
}
