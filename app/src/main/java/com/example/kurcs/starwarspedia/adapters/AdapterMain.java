package com.example.kurcs.starwarspedia.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kurcs.starwarspedia.R;
import com.example.kurcs.starwarspedia.activities.DetailsActivity;
import com.example.kurcs.starwarspedia.api.Result;

import java.util.ArrayList;

/**
 * Created by kurcs on 25.09.2017.
 */

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    public ArrayList<Result> results = new ArrayList<>();
    public static int COUNTER = 0;

    public AdapterMain(ArrayList<Result> results) { //konstruktor adaptera
        this.results = results;
    }

    @Override
    public AdapterMain.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //znalezienie widoku jednego przedmiotu
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, null, false);
        AdapterMain.ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterMain.ViewHolder holder, int position) {
        Result result = results.get(position); // przypisanie odowiedniego obiektu do pozycji w liście
        holder.textViewName.setText(result.name); // pobranie wartości z obiektu i przypisanie do pól
        holder.textViewHomeWorld.setText("Kolor Skóry: " + result.skinColor);
        holder.textViewHeight.setText("Wzrost: " + result.height + " cm");
        holder.cardViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COUNTER = holder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                view.getContext().startActivity(intent); // po kliknięciu w element przechodzimy do szczegółów
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size(); // wielkość listy
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ // poszczególne pola w jednym przedmiocie

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
