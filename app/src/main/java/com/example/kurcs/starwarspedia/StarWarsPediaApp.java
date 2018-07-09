package com.example.kurcs.starwarspedia;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kurcs on 25.09.2017.
 */

public class StarWarsPediaApp extends Application { // Klasa, która wykonuje zapytanie sieciowe

    private static String BASE_URL = "https://swapi.co/api/";

    Retrofit retrofit;
    public static Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); //inicjalizacja bazy danych
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build(); // tworzymy obiekt retrofit
        api = retrofit.create(Api.class); // przypisanie interfejsu

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build(); // tworzymy konfigurację bazy danych
        Realm.setDefaultConfiguration(realmConfiguration); // tworzymy podstawowe ustawienie bazy. Jeżeli obiekt zmieni wartości, to cała baza danych zostanie ususnięta i stworzona na nowo
    }
}
