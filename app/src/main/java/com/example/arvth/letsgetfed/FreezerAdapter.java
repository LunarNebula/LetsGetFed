package com.example.arvth.letsgetfed;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */
public class FreezerAdapter extends RecyclerView.Adapter<FreezerAdapter.ViewHolder>{
    // Data
    private static final String TAG = "FreezerAdapter";

    private ArrayList<Food> foodNames;
    private Context recycleShelfContext;

    // Constructors

    /**
     * Constructs an object of the FreezerAdapter class with a given ArrayList of foods and a given context
     * @param mFoodNames the given ArrayList of foods (Food object)
     * @param mRecycleShelfContext the given context
     */
    public FreezerAdapter (ArrayList<Food> mFoodNames, Context mRecycleShelfContext)
    {
        recycleShelfContext = mRecycleShelfContext;
        foodNames = mFoodNames;
        for (int j = 0; j < foodNames.size(); j++) {
            if (foodNames.get(j).getLocation() != 2) {
                foodNames.remove(j);
                j--;
            }
        }
    }

    /**
     *
     * @param parent
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shelf, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.foodName.setText(foodNames.get(i).getName());
        viewHolder.mainConstraintLayout2.setOnClickListener(new View.OnClickListener(){

            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + foodNames.get(i).toString2());
                Toast.makeText(recycleShelfContext, foodNames.get(i).getName(), Toast.LENGTH_SHORT).show();

                //Local Broadcast to Delete Foods
                String clickedFoodName = foodNames.get(i).getName();
                Intent intent = new Intent("From FreezerAdapter");
                intent.putExtra("food name", clickedFoodName);
                intent.putExtra("position in shelf", i);
                LocalBroadcastManager.getInstance(recycleShelfContext).sendBroadcast(intent);
            }
        });
    }

    /**
     * This method returns the number of items in the ArrayList/shelf
     * @return the number of items in the ArrayList/shelf
     */
    @Override
    public int getItemCount() {
        return foodNames.size();
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView foodName;
        ConstraintLayout mainConstraintLayout2;

        /**
         *
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_name_show);
            mainConstraintLayout2 = itemView.findViewById(R.id.main_constraint_layout2);
        }
    }
}
