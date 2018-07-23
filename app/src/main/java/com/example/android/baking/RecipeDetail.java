package com.example.android.baking;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;

public class RecipeDetail extends AppCompatActivity implements StepFragment.Communicator{

    RecipeDetailFragment recipeDetailFragment;
    StepInstructionFragment stepInstructionFragment;
    android.support.v4.app.FragmentManager manager;
    StepFragment stepFragment;
    StepInstructionFragment sIF;
    RecipeDetailFragment rIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        final Recipe currentRecipe = getIntent().getParcelableExtra("Recipe");

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Recipe", currentRecipe);
        recipeDetailFragment.setArguments(bundle);


        manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, recipeDetailFragment, "Detail");
        Log.v("Master", "Adding RecipeDetailFragment");

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
            fragmentTransaction.add(R.id.stepInstruction_fragment_container, stepInstructionFragment, "Instruct");

        }
        fragmentTransaction.commit();

        //sF.setCommunicator(this);


    }

    @Override
    public void respond(Steps currentSteps) {

        Log.v("currentSteps",currentSteps.toString());

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

    /*@Override
    public void respond(View view, Steps currentSteps) {


        //if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){

            //Bundle bundle = new Bundle();
            //Log.v("currentSteps",currentSteps.toString());
            //bundle.putParcelable("Steps", currentSteps);
            //StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
            //stepInstructionFragment.setArguments(bundle);




                FragmentManager fm = getSupportFragmentManager();

                if (fm == null) {
                    Toast.makeText(this, "No Fragment Manager", Toast.LENGTH_SHORT).show();
                }

                Fragment sv = fm.findFragmentByTag("Instruct");
        if (sv == null) {
            Toast.makeText(this, "No Fragment Here", Toast.LENGTH_SHORT).show();
        }

                //fm.beginTransaction()
                       // .replace(R.id.stepInstruction_fragment_container, stepInstructionFragment).commit();




        //} else {
        //    Intent intent = new Intent(context, StepInstruction.class);
        //    intent.putExtra("Package",currentSteps);
            //context.startActivity(intent);
        //}

    }
    */

}
