package com.example.countrismusic;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrismusic.api_handler.APIClient;
import com.example.countrismusic.api_handler.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Country> countries = new ArrayList<>();
    private CardAdapter adapter;
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.countriesList);


        adapter = new CardAdapter(this, countries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fillCards();
    }

    private void fillCards() {

        Call<List<Country>> call = apiInterface.getAllCountries("name,continents,flags");

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    countries.clear();
                    countries.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                     Log.e("API", "Error: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
