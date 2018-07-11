package com.example.android.baking;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getBoolean(R.bool.isTab)) {
            setContentView(R.layout.activity_recipe_detail);

            //FragmentManager fragmentManager = getFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //Initial_Mobile_Fragement ls_fragment = new Initial_Mobile_Fragement();
            //fragmentTransaction.replace(android.R.id.content, ls_fragment);

        } else {
            setContentView(R.layout.activity_recipe_detail_mobile);
        }


        final Recipe currentRecipe = getIntent().getParcelableExtra("Recipe");

        ArrayList<Ingredients> ingredientList = currentRecipe.getIngredients();
        for (int j = 0; j < ingredientList.size(); j++) {
            Log.v(TAG, "Ingredient: " + ingredientList.get(j).getIngredient() + "\n" +
                    "Quantity: " + ingredientList.get(j).getQuantity() + "\n" +
                    "Measure: " + ingredientList.get(j).getMeasure() + "\n"
            );
        }

        RecyclerView mRecyclerView = findViewById(R.id.ingredient_recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalReview = new LinearLayoutManager(RecipeDetail.this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(RecipeDetail.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(RecipeDetail.this, horizontalReview.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);

        IngredientAdapter mAdapter = new IngredientAdapter(RecipeDetail.this,ingredientList);
        mRecyclerView.setAdapter(mAdapter);

    }




}
