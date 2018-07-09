package com.example.kurcs.starwarspedia.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.adapters.AdapterFavourites;
import com.example.kurcs.starwarspedia.api.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.kurcs.starwarspedia.activities.MainActivity.favourites;

/**
 * Created by kurcs on 25.09.2017.
 */

public class FavouritesDetailActivity extends AppCompatActivity {

    Realm realm;

    @BindView(R.id.detailsBirthYear) TextView birthYear;
    @BindView(R.id.detailsGender) TextView gender;
    @BindView(R.id.detailsHairColor) TextView hairColor;
    @BindView(R.id.detailsHeight) TextView height;
    @BindView(R.id.detailsMass) TextView mass;
    @BindView(R.id.detailsSkinColor) TextView skinColor;
    @BindView(R.id.textViewDetailsName) TextView name;
    @BindView(R.id.fabAddTofavourites)
    FloatingActionButton fabAdd;
    @BindView(R.id.fabDeleteFromFavourites) FloatingActionButton fabRemove;
    @BindView(R.id.textViewState) TextView textViewState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        final Result result = ActivityFavourites.favouriteResults.get(AdapterFavourites.COUNTER); // pobranie obiektu z listy za pomocą
        //licznika z adaptera

        birthYear.setText("Rok urodzenia: " + result.birthYear); //przypisanie wartości do pól
        gender.setText("Płeć: " + result.gender);
        hairColor.setText("Kolor włosów: " + result.hairColor);
        height.setText("Wzrost: " + result.height + " cm");
        mass.setText("Waga: " + result.mass);
        skinColor.setText("Kolor Skóry: " + result.skinColor);
        name.setText(result.name);

        if(result.isFavourite){
            fabAdd.setVisibility(View.GONE);
            fabRemove.setVisibility(View.VISIBLE);
            textViewState.setText("Usuń z ulubionych");
        }else{
            fabAdd.setVisibility(View.VISIBLE);
            fabRemove.setVisibility(View.GONE);
            textViewState.setText("Dodaj do ulubionych");
        }

        fabRemove.setOnClickListener(new View.OnClickListener() { //usuwanie obiektu z bazy danych
            @Override
            public void onClick(View view) {
                favourites.remove(result);
                ActivityFavourites.favouriteResults.remove(result);
                realm.beginTransaction();
                result.isFavourite = false;
                result.deleteFromRealm();
                realm.commitTransaction();
                FavouritesDetailActivity.this.finish();
                ActivityFavourites.adapterFavourites.notifyDataSetChanged();
            }
        });
    }

}
