package com.example.android.baking;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;

public class RecipeDetail extends AppCompatActivity implements StepFragment.Communicator{

    android.support.v4.app.FragmentManager manager;
    int recipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if(savedInstanceState == null) {

            final Recipe currentRecipe = getIntent().getParcelableExtra("Recipe");
            recipeImage = getIntent().getIntExtra("Image", 0);

            setTitle(currentRecipe.getName());

            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Recipe", currentRecipe);
            bundle.putInt("Image", recipeImage);
            recipeDetailFragment.setArguments(bundle);

            manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, recipeDetailFragment, "Detail");

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
                fragmentTransaction.add(R.id.stepInstruction_fragment_container, stepInstructionFragment, "Instruct");

            }
            fragmentTransaction.commit();

        }
    }

    @Override
    public void respond(Steps currentSteps) {

       if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            StepInstructionFragment newFragment = new StepInstructionFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Package", currentSteps);
            newFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.stepInstruction_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            Intent intent = new Intent(this, StepInstruction.class);
            intent.putExtra("Package",currentSteps);
            startActivity(intent);
        }

    }

}
