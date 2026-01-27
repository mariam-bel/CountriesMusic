package com.example.countrismusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyHolder> {

    private final Context context;
    private final ArrayList<Country> countries;

    public CardAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_cv_row, parent);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyHolder holder, int position) {
        Country country = countries.get(position);

        holder.countryName.setText(country.name.common);
        holder.countryContinent.setText(country.continents);
        holder.countryFlag.setText(country.flags.png);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView countryName, countryContinent, countryFlag;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            countryName = itemView.findViewById(R.id.countryName);
            countryContinent = itemView.findViewById(R.id.countryContintent);
            countryFlag = itemView.findViewById(R.id.countryFlag);
        }
    }
}
