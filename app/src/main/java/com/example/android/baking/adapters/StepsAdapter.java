package com.example.android.baking.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.baking.R;
import com.example.android.baking.StepInstruction;
import com.example.android.baking.model.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private ArrayList<Steps> mDataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mshortDesc;

        public ViewHolder (View v){
            super(v);
            this.mshortDesc = (TextView) v.findViewById(R.id.step);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(context, StepInstruction.class);
                    Steps currentSteps = mDataset.get(pos);
                    intent.putExtra("Package",currentSteps);
                    context.startActivity(intent);
                }
            });
        }
    }

    public StepsAdapter(Context context, ArrayList<Steps> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_recycler_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(StepsAdapter.ViewHolder holder, int position) {
        Log.v("Recycler", Integer.toString(position));
        holder.mshortDesc.setText(mDataset.get(position).getShortDescription());
    }

    public int getItemCount() {

        return mDataset.size();
    }
}
