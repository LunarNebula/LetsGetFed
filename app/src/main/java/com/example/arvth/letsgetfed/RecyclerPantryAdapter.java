package com.example.arvth.letsgetfed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerPantryAdapter extends RecyclerView.Adapter<RecyclerPantryAdapter.ViewHolder>{
    // Data
    private static final String TAG = "RecyclerPantryAdapter";

    private ArrayList<Shelf> shelfNames;
    private Context recyclePantryContext;

    // Constructors
    public RecyclerPantryAdapter (ArrayList<Shelf> mShelfNames, Context mRecyclePantryContext)
    {
        shelfNames = mShelfNames;
        recyclePantryContext = mRecyclePantryContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_pantry, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.shelfName.setText(shelfNames.get(position).getLabel());
        viewHolder.mainConstraintLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + shelfNames.get(position));
                Toast.makeText(recyclePantryContext, shelfNames.get(position).getLabel(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shelfNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView shelfName;
        ConstraintLayout mainConstraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shelfName = itemView.findViewById(R.id.shelf_name_show);
            mainConstraintLayout = itemView.findViewById(R.id.main_constraint_layout);
        }
    }
}
