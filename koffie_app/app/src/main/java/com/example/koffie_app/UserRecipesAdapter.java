package com.example.koffie_app;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserRecipesAdapter extends RecyclerView.Adapter<UserRecipesAdapter.UserRecipesViewHolder>{
    private String[] userRecipes;

    public UserRecipesAdapter (String[] recipes){
        this.userRecipes = recipes;
    }

    public static class UserRecipesViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public UserRecipesViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.userRecipeTitle);
        }
    }

    @NonNull
    @Override
    public UserRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.l_user_recipe_card,parent,false);
        UserRecipesViewHolder userRecipesViewHolder = new UserRecipesViewHolder(v);
        return userRecipesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecipesViewHolder holder, int position) {
        holder.textView.setText(userRecipes[position]);
    }

    @Override
    public int getItemCount() {
        return userRecipes.length;
    }
}
