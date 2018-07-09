package com.example.kurcs.starwarspedia.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.adapters.AdapterMain;
import com.example.kurcs.starwarspedia.api.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.kurcs.starwarspedia.activities.MainActivity.favourites;

/**
 * Created by kurcs on 25.09.2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private boolean isFavourite;
    private Realm realm;

    @BindView(R.id.detailsBirthYear) TextView birthYear; // znajduje wszystkie elementy w widoku
    @BindView(R.id.detailsGender) TextView gender;
    @BindView(R.id.detailsHairColor) TextView hairColor;
    @BindView(R.id.detailsHeight) TextView height;
    @BindView(R.id.detailsMass) TextView mass;
    @BindView(R.id.detailsSkinColor) TextView skinColor;
    @BindView(R.id.textViewDetailsName) TextView name;
    @BindView(R.id.fabAddTofavourites) FloatingActionButton fabAdd;
    @BindView(R.id.fabDeleteFromFavourites) FloatingActionButton fabRemove;
    @BindView(R.id.textViewState) TextView textViewState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        final Result result = MainActivity.results.get(AdapterMain.COUNTER);

        birthYear.setText("Rok urodzenia: " + result.birthYear); //ustawienie pól za pomocą danych z obiektu
        gender.setText("Płeć: " + result.gender);
        hairColor.setText("Kolor włosów: " + result.hairColor);
        height.setText("Wzrost: " + result.height + " cm");
        mass.setText("Waga: " + result.mass);
        skinColor.setText("Kolor Skóry: " + result.skinColor);
        name.setText(result.name);

        fabAdd.setVisibility(View.VISIBLE);
        fabRemove.setVisibility(View.GONE);
        textViewState.setText("Dodaj do ulubionych");


        fabAdd.setOnClickListener(new View.OnClickListener() { //przycisk do dodawania postaci do ulubionych
            @Override
            public void onClick(View view) {
                result.isFavourite = true;
                realm.beginTransaction(); // zaczynamy transakcję
                realm.copyToRealm(result); // kopiujemy do bazy obiekt
                realm.commitTransaction(); // zatwierdzamy transakcję
                favourites.add(0, result); // dodajemy obiekt do naszej listy
                fabAdd.setVisibility(View.GONE); // ukrywamy przycisk
                textViewState.setVisibility(View.GONE);
            }
        });

    }

}
