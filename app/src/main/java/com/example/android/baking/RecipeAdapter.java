package com.example.android.baking;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.baking.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> mDataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mPhoto;
        public TextView mName;
        public TextView mAge;
        public CardView cv;

        public ViewHolder (View v){
            super(v);
            mPhoto = v.findViewById(R.id.item_photo);
            mName = v.findViewById(R.id.item_name);
            mAge = v.findViewById(R.id.person_age);
            cv = v.findViewById(R.id.cv);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(context, RecipeDetail.class);
                    Recipe currentRecipe = mDataset.get(pos);
                    intent.putExtra("Recipe",currentRecipe);

                    context.startActivity(intent);
                }
            });


        }

    }

    public RecipeAdapter(Context context, ArrayList<Recipe> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.initial_recipe_cardview_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {

        holder.mName.setText(mDataset.get(position).getName());
        holder.mAge.setText(mDataset.get(position).getId());
        Picasso.with(context).setLoggingEnabled(true);
        //Picasso.with(context).load("http://www.pngall.com/food-png").fit().placeholder(R.drawable.ic_launcher_foreground).into(holder.mPhoto);

    }

    @Override
    public int getItemCount() {
        Log.v("Tag", Integer.toString(mDataset.size()));
        return mDataset.size();


    }

}
