package com.example.koffie_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class CoffeeOverviewAdapter extends RecyclerView.Adapter<CoffeeOverviewAdapter.CoffeeOverviewViewHolder> {

    private Coffee[] coffees;

    public CoffeeOverviewAdapter(Coffee[] coffees){
        this.coffees = coffees;

    }

    // internal class
    public static class CoffeeOverviewViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;

        public CoffeeOverviewViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.coffeecard_textView);
            imageView = v.findViewById(R.id.imageView);
        }
//        setImageResource(R.drawable.my_image);
    }

}
