package com.example.android.baking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;

import java.util.ArrayList;


public class IngredientFragment extends Fragment {

    ListView list;
    ArrayList<String> ingredientArray = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        list = view.findViewById(R.id.ingredientlistview);


        Bundle bundle = getArguments();
        Log.v("Bundle", bundle.toString());
        if (bundle != null){

            final Recipe currentRecipe = bundle.getParcelable("Recipe");
            ArrayList<Ingredients> ingredientList = currentRecipe.getIngredients();

            for (int j = 0; j < ingredientList.size(); j++) {
                //Log.v("Frag", ingredientList.get(j).getQuantity() + " " + ingredientList.get(j).getMeasure() + " " + ingredientList.get(j).getIngredient());
                String ingredientRow = ingredientList.get(j).getQuantity() + " " + ingredientList.get(j).getMeasure() + " " + ingredientList.get(j).getIngredient();
                ingredientArray.add(ingredientRow);
            }

        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, ingredientArray);
        list.setAdapter(adapter);

        return view;
    }
}
