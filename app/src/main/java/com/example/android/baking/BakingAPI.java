package com.example.android.baking;

import com.example.android.baking.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BakingAPI {


    // Add Base URL
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @Headers("Content-Type: application/json")
    @GET("baking.json")
    Call<ArrayList<Recipe>> getData();

}
