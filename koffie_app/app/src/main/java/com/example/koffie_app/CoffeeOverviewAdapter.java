package com.example.koffie_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

class CoffeeOverviewAdapter extends RecyclerView.Adapter<CoffeeOverviewAdapter.CoffeeOverviewViewHolder> {

    private Coffee[] coffees;
    private Context context;

    public CoffeeOverviewAdapter(Coffee[] coffees, Context context){
        this.coffees = coffees;
        this.context = context;
    }

    // internal class
    public static class CoffeeOverviewViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDesc;
        public ImageView imageView;

        public CoffeeOverviewViewHolder(View v, Context context){
            super(v);
            textViewTitle = v.findViewById(R.id.coffeecard_textView);
            textViewTitle.setTypeface(ResourcesCompat.getFont(context, R.font.rock_salt_regular));
            textViewDesc= v.findViewById(R.id.coffeecard_textView_desc);
            textViewDesc.setTypeface(ResourcesCompat.getFont(context, R.font.rock_salt_regular));
//            textView.setTextColor(Color.parseColor("#FFFFFF"));
            imageView = v.findViewById(R.id.coffeecard_imageView);

//            textView.setTypeface(ResourcesCompat.getFont(context, R.font.RockSalt_Regular));
        }
    }

    @NonNull
    @Override
    public CoffeeOverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_overview_card,parent,
                false);
        CoffeeOverviewViewHolder coffeeOverviewViewHolder = new CoffeeOverviewViewHolder(v, context);
        return coffeeOverviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeOverviewViewHolder holder, int position) {
        holder.textViewTitle.setText(coffees[position].getName());
        holder.textViewTitle.setTextColor(Color.parseColor(coffees[position].getColorCode()));
        holder.imageView.setImageResource(R.drawable.dummy_coffee);      // TODO: 05/07/2020 retrieve images
        // from room db
    }

    @Override
    public int getItemCount() {
        return coffees.length;
    }

}
