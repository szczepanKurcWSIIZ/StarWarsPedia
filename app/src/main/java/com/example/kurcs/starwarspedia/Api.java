package com.example.kurcs.starwarspedia;

import com.example.kurcs.starwarspedia.api.Response;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kurcs on 25.09.2017.
 */

public interface Api { //interfejs, przez który odwołujemy się do Api

    @GET("people/")
    Call<Response> getCharacters();
}
