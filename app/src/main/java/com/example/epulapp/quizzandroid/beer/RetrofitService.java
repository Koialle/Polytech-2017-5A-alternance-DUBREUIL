package com.example.epulapp.quizzandroid.beer;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("beers")
    Call<List<Beer>> getBeers();

    @GET("beers/{id}")
    Call<List<Beer>> getBeer(@Path("id") int id);
}
