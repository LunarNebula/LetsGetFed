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

/**
 * This class changes the traits of a group of objects to put into a RecyclerView (for the counter)
 */
public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder>{
    // Data
    private static final String TAG = "CounterAdapter";
    private ArrayList<Food> foodNames;
    private Context recycleShelfContext;

    // Constructors
    /**
     * Constructs an object of the CounterAdapter class with a given ArrayList of foods and a given Context
     * @param mFoodNames the given Arraylist of foods (Food objects)
     * @param mRecycleShelfContext the given context
     */
    public CounterAdapter (ArrayList<Food> mFoodNames, Context mRecycleShelfContext)
    {
        Log.d(TAG, "In the constructor");
        recycleShelfContext = mRecycleShelfContext;
        foodNames = mFoodNames;
        for (int j = 0; j < foodNames.size(); j++) {
        }

        Log.d(TAG, Integer.toString(foodNames.size()));
    }

    /**
     * This method builds a ViewHolder that holds recycle_shelf with a given ViewGroup and integer position
     * @param parent the given ViewGroup
     * @param i the given integer position
     * @return the ViewHolder
     */
    @NonNull
    @Override
    public CounterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shelf, parent, false);
        CounterAdapter.ViewHolder viewHolder = new CounterAdapter.ViewHolder(view);
        return viewHolder;
    }

    /**
     * This method sets the content of the given ViewHolder with a given integer position
     * @param viewHolder the given ViewHolder
     * @param i the given integer position
     */
    @Override
    public void onBindViewHolder(@NonNull CounterAdapter.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.foodName.setText(foodNames.get(i).getName());
        viewHolder.mainConstraintLayout2.setOnClickListener(new View.OnClickListener(){

            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + foodNames.get(i));
                Toast.makeText(recycleShelfContext, foodNames.get(i).getName(), Toast.LENGTH_SHORT).show();
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
     * This class models the behavior of a ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView foodName;
        ConstraintLayout mainConstraintLayout2;

        /**
         * Constructs a ViewHolder with a given View
         * @param itemView the given View
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_name_show);
            mainConstraintLayout2 = itemView.findViewById(R.id.main_constraint_layout2);
        }
    }
}
