package com.example.kurcs.starwarspedia.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.adapters.AdapterFavourites;
import com.example.kurcs.starwarspedia.api.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kurcs on 25.09.2017.
 */

public class ActivityFavourites extends AppCompatActivity {

    public static AdapterFavourites adapterFavourites;
    Realm realm;

    public static ArrayList<Result> favouriteResults;

    @BindView(R.id.recyclerMain) RecyclerView recyclerView; // przypisanie przycisków do layoutu
    @BindView(R.id.progressBarMain) ProgressBar progressBarMain;
    @BindView(R.id.textViewNoContent) TextView textViewNoContent;
    @BindView(R.id.fabFavourites) FloatingActionButton fabFavourites;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //inicjalizacja biblioteki butter knife
        fabFavourites.setVisibility(View.GONE);
        realm = Realm.getDefaultInstance(); // przypisanie bazie danych domyślnych ustawień
        favouriteResults = new ArrayList<>();  //nowa lista dla obiektów

        adapterFavourites = new AdapterFavourites(favouriteResults); // stworzenie adaptera dla list
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // liniowy manager dla listy
        recyclerView.setHasFixedSize(true); // stała wysokość listy
        recyclerView.setAdapter(adapterFavourites); //przypisanie adaptera
    }

    @Override
    protected void onResume() {
        super.onResume();
        RealmResults<Result> results = realm.where(Result.class).findAll(); //na wznowieniu aktywności przechodzimy po elementach listy z bazy lokalnej i dodajemy je do adaptera
        if(results.size() > 0){
            for (Result result: results){
                if(!adapterFavourites.results.contains(result)){
                    favouriteResults.add(0,result);
                }
            }
            progressBarMain.setVisibility(View.GONE);
            if(favouriteResults.size() == 0){
                textViewNoContent.setVisibility(View.VISIBLE);
            }else{
                textViewNoContent.setVisibility(View.GONE);
            }
            adapterFavourites.notifyDataSetChanged();// informujemy adapter o zmianach
        }
    }

}
