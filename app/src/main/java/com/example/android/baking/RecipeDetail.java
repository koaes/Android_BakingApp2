package com.example.android.baking;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.baking.model.Recipe;

public class RecipeDetail extends AppCompatActivity implements StepFragment.Communicator{

    android.support.v4.app.FragmentManager manager;
    int recipeImage;
    Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if(savedInstanceState!=null){

            currentRecipe = savedInstanceState.getParcelable("recipe");
        } else {

            currentRecipe = getIntent().getParcelableExtra("Recipe");
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
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putParcelable("recipe", currentRecipe);

    }

    @Override
    public void respond(int position) {

       if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            StepInstructionFragment newFragment = new StepInstructionFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("Package", currentRecipe);
            bundle.putInt("Position",position);
            newFragment.setArguments(bundle);

            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.stepInstruction_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            Intent intent = new Intent(this, StepInstruction.class);
            intent.putExtra("Package",currentRecipe);
            intent.putExtra("Position", position);
            startActivity(intent);
        }

    }

}
