package com.example.kurcs.starwarspedia.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.activities.FavouritesDetailActivity;
import com.example.kurcs.starwarspedia.api.Result;

import java.util.ArrayList;

/**
 * Created by kurcs on 25.09.2017.
 */

public class AdapterFavourites extends RecyclerView.Adapter<AdapterFavourites.ViewHolder> { //adapter dla elementów w tablicy

    public ArrayList<Result> results = new ArrayList<>();
    public static int COUNTER = 0;

    public AdapterFavourites(ArrayList<Result> results) { //konstruktor adaptera
        this.results = results;
    }

    @Override
    public AdapterFavourites.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, null, false); //znalezienie widoku przedmiotu
        AdapterFavourites.ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Result result = results.get(position); //pobranie elementu z danej pozycji
        holder.textViewName.setText(result.name); //uzupełnienie danych z obiektu
        holder.textViewHomeWorld.setText("Kolor Skóry: " + result.skinColor);
        holder.textViewHeight.setText("Wzrost: " + result.height + " cm");
        holder.cardViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COUNTER = holder.getAdapterPosition(); //na kliknięcie pobieramy pozycję adaptera i przekazujemy ją jako licznik
                Intent intent = new Intent(view.getContext(), FavouritesDetailActivity.class);
                view.getContext().startActivity(intent); // przechodzimy do nowej aktywności
            }
        });
    }


    @Override
    public int getItemCount() { //liczba przedmiotów w liście
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ // wszystkie pola w jednym przedmiocie

        TextView textViewName;
        CardView cardViewMain;
        TextView textViewHomeWorld;
        TextView textViewHeight;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewHomeWorld = itemView.findViewById(R.id.textViewHomeName);
            cardViewMain = itemView.findViewById(R.id.cardViewMain);
            textViewHeight = itemView.findViewById(R.id.textViewHeight);
        }
    }
}
