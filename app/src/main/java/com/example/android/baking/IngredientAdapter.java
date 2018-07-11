package com.example.android.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.baking.model.Ingredients;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder>{

    private List<Ingredients> ingredientsList;
    private Context context;

    public IngredientAdapter(Context context, List<Ingredients> ingredientsList ){
        this.ingredientsList = ingredientsList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mIngredient;
        public TextView mQuantity;
        public TextView mMeasure;

        public MyViewHolder(View view){
            super(view);
            mIngredient = view.findViewById(R.id.ingredient);
            mQuantity = view.findViewById(R.id.quantity);
            mMeasure = view.findViewById(R.id.measure);
        }
    }

    @Override
    public IngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_recycler_row, parent, false);
        return new IngredientAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.MyViewHolder holder, int position) {
        Ingredients ingredients = ingredientsList.get(position);

        holder.mIngredient.setText(ingredients.getIngredient());
        holder.mQuantity.setText(ingredients.getQuantity());
        holder.mMeasure.setText(ingredients.getMeasure());

    }


    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

}
