package com.example.android.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.android.baking.adapters.RecipeAdapter;
import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ARRAY_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getArrayData();
    }

    private void getArrayData() {
        //Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ARRAY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Api
        BakingAPI bakingAPI = retrofit.create(BakingAPI.class);

        Call<ArrayList<Recipe>> call = bakingAPI.getData();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {


                ArrayList<Recipe> feedList = response.body();

                for (int i = 0; i < feedList.size(); i++) {

                    ArrayList<Ingredients> ingredientList = feedList.get(i).getIngredients();
                    for (int j = 0; j < ingredientList.size(); j++) {
                    }

                }

                RecyclerView mRecyclerView = findViewById(R.id.initial_recycler_view);
                mRecyclerView.setHasFixedSize(true);

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                RecipeAdapter mAdapter = new RecipeAdapter(MainActivity.this,feedList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.v(TAG, "Something went wrong: " + t.getMessage());
            }
        });

    }


}
