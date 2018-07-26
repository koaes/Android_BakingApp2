package com.example.android.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.baking.model.Recipe;


public class StepInstruction extends AppCompatActivity {

    android.support.v4.app.FragmentManager manager;
    Recipe current;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        getSupportActionBar().hide();

        if(savedInstanceState == null) {


            current = getIntent().getParcelableExtra("Package");
            position = getIntent().getIntExtra("Position",0);

            StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Package", current);
            bundle.putInt("Position", position);
            stepInstructionFragment.setArguments(bundle);

            manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.stepInstruction_fragment_container, stepInstructionFragment);
            fragmentTransaction.commit();

        }
    }




}
