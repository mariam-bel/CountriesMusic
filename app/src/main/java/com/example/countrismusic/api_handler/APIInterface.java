package com.example.countrismusic.api_handler;

import com.example.countrismusic.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("all")
    Call<List<Country>> getAllCountries(
            @Query("fields") String fields
    );

    @GET("name/{name}")
    Call<List<Country>> getCountryByName(@Path("name") String name);

    @GET("region/{region}")
    Call<List<Country>> getCountriesByRegion(@Path("region") String region);
}
