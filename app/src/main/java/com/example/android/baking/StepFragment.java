package com.example.android.baking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.baking.adapters.StepsAdapter;
import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;

import java.util.ArrayList;

public class StepFragment extends Fragment {

    ListView stepsView;
    ArrayList<String> stepsArray = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps, container, false);



        Bundle bundle = getArguments();
        final Recipe currentRecipe = bundle.getParcelable("Recipe");
        final ArrayList<Steps> stList = currentRecipe.getSteps();

        for (int j = 0; j < stList.size(); j++) {


            String stepRow = stList.get(j).getShortDescription();
            stepsArray.add(stepRow);

            //Log.v(TAG, "Description: " + stepsList.get(j).getDescription() + "\n" +
            //"Short: " + stepsList.get(j).getShortDescription() + "\n" +
            // "Video: " + stepsList.get(j).getVideoURL() + "\n" +
            //"Thumbnail: " + stepsList.get(j).getThumbnailURL() + "\n"
            //);
        }

        stepsView = view.findViewById(R.id.stepslistview);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stepsArray);
        stepsView.setAdapter(adapter);

        stepsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), StepInstruction.class);
                Steps currentSteps = stList.get(position);
                intent.putExtra("Package",currentSteps);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
