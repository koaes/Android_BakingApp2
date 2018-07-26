package com.example.android.baking;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;
import java.util.ArrayList;

public class StepFragment extends Fragment {

    ListView stepsView;
    ArrayList<String> stepsArray = new ArrayList<String>();
    Communicator communicator;
    int stepPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        Bundle bundle = getArguments();
        final Recipe currentRecipe = bundle.getParcelable("Recipe");


        //Get the steps for the recipe for the listview
        final ArrayList<Steps> stList = currentRecipe.getSteps();
        for (int j = 0; j < stList.size(); j++) {
            String stepRow = stList.get(j).getShortDescription();
            stepsArray.add(stepRow);
        }

        stepsView = view.findViewById(R.id.stepslistview);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stepsArray);
        stepsView.setAdapter(adapter);

        stepsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                stepPosition = position;
                callRespond(stepPosition);
            }
        });

        return view;
    }


    public interface Communicator{
        void respond (int stepPosition);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Communicator){
            communicator = (Communicator) context;
        } else {
            throw new ClassCastException(context.toString()
            + " must implement Communicator.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        communicator = null;
    }

    public void callRespond(int position){
        communicator.respond(position);
    }

}
