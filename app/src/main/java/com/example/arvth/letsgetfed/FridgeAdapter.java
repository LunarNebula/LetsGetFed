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

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder>{
    // Data
    private static final String TAG = "FridgeAdapter";

    private ArrayList<Food> foodNames;
    private Context recycleShelfContext;

    // Constructors

    /**
     * Constructs an object of the FridgeAdapter class with a given ArrayList of foods and a given context
     * @param mFoodNames the given ArrayList of foods (Food objects)
     * @param mRecycleShelfContext the given context
     */
    public FridgeAdapter (ArrayList<Food> mFoodNames, Context mRecycleShelfContext)
    {
        recycleShelfContext = mRecycleShelfContext;
        foodNames = mFoodNames;
        for (int j = 0; j < foodNames.size(); j++) {
            if (foodNames.get(j).getLocation() != 1) {
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
                Log.d(TAG, "onClick: clicked on " + foodNames.get(i));
                Toast.makeText(recycleShelfContext, foodNames.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * @return
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