package com.example.kurcs.starwarspedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.StarWarsPediaApp;
import com.example.kurcs.starwarspedia.adapters.AdapterMain;
import com.example.kurcs.starwarspedia.api.Response;
import com.example.kurcs.starwarspedia.api.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    public static ArrayList<Result> results; //lista dla głównej aktywności
    public static ArrayList<Result> favourites; // lista dla ulubionych
    private AdapterMain adapterMain;
    Result result;
    private Realm realm;

    @BindView(R.id.recyclerMain) RecyclerView recyclerView;
    @BindView(R.id.progressBarMain) ProgressBar progressBarMain;
    @BindView(R.id.textViewNoContent) TextView textViewNoContent;
    @BindView(R.id.fabFavourites) FloatingActionButton fabFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        results = new ArrayList<>();
        favourites = new ArrayList<>();
        adapterMain = new AdapterMain(results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // przypisanie wartości do listy (layout manager, adapter)
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMain);

        StarWarsPediaApp.api.getCharacters().enqueue(new Callback<Response>() { //callback do zapytania sieciowego
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d(TAG, "onResponse: ");
                progressBarMain.setVisibility(View.GONE);
                if(response.body() != null){ //sprawdzenie czy odpowiedź nie jest null
                    for(int i=0; i<response.body().results.size(); i++){
                        result = response.body().results.get(i); // dodanie wszystkich wartości do tablicy
                        result.isFavourite = false;
                        if(favourites.size() > 0){
                            for(int j=0; j< favourites.size(); j++){
                                if(result.url == favourites.get(j).url){
                                    result.isFavourite = true; // porównanie wszystkich wartości z tymi w tablicy
                                }
                            }
                        }
                        results.add(result);
                    }
                }
                adapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                progressBarMain.setVisibility(View.GONE); // jeżeli połączenie się nie powiedzie, wyświetlamy wiadomość
                textViewNoContent.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        fabFavourites.setOnClickListener(new View.OnClickListener() { // przycisk do aktywności ulubionych
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityFavourites.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RealmResults<Result> results = realm.where(Result.class).findAll(); // znajdujemy wszystkie wartości w bazie danych
        if(results.size() > 0){
            for (Result result: results){
                favourites.add(0,result); // umieszczamy wartości w tablicy
            }
        }
    }
}
