package com.example.countrismusic;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrismusic.api_handler.APIClient;
import com.example.countrismusic.api_handler.APIInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputEditText editText;
    private final ArrayList<Country> countries = new ArrayList<>();
    private CardAdapter adapter;
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.countriesList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        editText = findViewById(R.id.buscador);

        adapter = new CardAdapter(this, countries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        fillCards();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                fillCards();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }

    private void searchByName(String name) {
        Call<List<Country>> call = apiInterface.getCountryByName(name);

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("AAA", response.body().toString());
                    countries.clear();
                    countries.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fillCards() {

        Call<List<Country>> call = apiInterface.getAllCountries("name,continents,flags");

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("AAA", response.body().toString());
                    countries.clear();
                    for (Country c: response.body()) {
                        Log.d("BBB", c.name.common);
                        countries.add(c);
                    }
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
