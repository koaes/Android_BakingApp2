package com.example.android.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.baking.adapters.IngredientAdapter;
import com.example.android.baking.adapters.StepsAdapter;
import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;

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
            //Log.v(TAG, "Ingredient: " + ingredientList.get(j).getIngredient() + "\n" +
            //        "Quantity: " + ingredientList.get(j).getQuantity() + "\n" +
            //        "Measure: " + ingredientList.get(j).getMeasure() + "\n"
            //);
        }

        ArrayList<Steps> stepsList = currentRecipe.getSteps();
        for (int j = 0; j < stepsList.size(); j++) {
            Log.v(TAG, "Description: " + stepsList.get(j).getDescription() + "\n" +
                    "Short: " + stepsList.get(j).getShortDescription() + "\n" +
                    "Video: " + stepsList.get(j).getVideoURL() + "\n" +
                    "Thumbnail: " + stepsList.get(j).getThumbnailURL() + "\n"
            );
        }





        RecyclerView mRecyclerView = findViewById(R.id.ingredient_recycler);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(RecipeDetail.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration itemDecor = new DividerItemDecoration(RecipeDetail.this, mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);

        IngredientAdapter mAdapter = new IngredientAdapter(RecipeDetail.this,ingredientList);
        mRecyclerView.setAdapter(mAdapter);



        RecyclerView mStepRecyclerView = findViewById(R.id.steps_recycler);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mstepLayoutManager = new LinearLayoutManager(RecipeDetail.this);
        mStepRecyclerView.setLayoutManager(mstepLayoutManager);

        DividerItemDecoration itemDecor2 = new DividerItemDecoration(RecipeDetail.this, mstepLayoutManager.getOrientation());
        mStepRecyclerView.addItemDecoration(itemDecor2);

        StepsAdapter mStepAdapter = new StepsAdapter(RecipeDetail.this,stepsList);
        mStepRecyclerView.setAdapter(mStepAdapter);



    }




}
