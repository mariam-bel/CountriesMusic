package com.example.countrismusic;

import com.example.countrismusic.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("all")
    Call<List<Country>> getAllCountries();

    @GET("name/{name}")
    Call<List<Country>> getCountryByName(@Path("name") String name);

    @GET("region/{region}")
    Call<List<Country>> getCountriesByRegion(@Path("region") String region);
}
