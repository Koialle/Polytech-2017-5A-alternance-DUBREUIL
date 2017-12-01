package com.example.epulapp.quizzandroid.beer;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * An interface used to get beer list from API
 * https://punkapi.com/documentation/v2
 */
public interface RetrofitService {
    @GET("beers")
    Call<List<Beer>> getBeers();
}
