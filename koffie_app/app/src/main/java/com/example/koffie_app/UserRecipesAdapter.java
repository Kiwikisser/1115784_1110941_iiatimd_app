package com.example.koffie_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserRecipesAdapter extends RecyclerView.Adapter<UserRecipesAdapter.UserRecipesViewHolder>{
    private List<UserRecipes> userRecipes = new ArrayList<>();

    @NonNull
    @Override
    public UserRecipesViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.l_user_recipe_card,parent,false);
        final Context context = parent.getContext();
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView textViewTitle = v.findViewById(R.id.userRecipeTitle);
                TextView textViewDescription= v.findViewById(R.id.userRecipeIngredient);
                String recipeSummaryTitle = textViewTitle.getText().toString();
                String recipeSummaryIngredients = textViewDescription.getText().toString();


                Bundle bundleForRecipeSummary = new Bundle();
                bundleForRecipeSummary.putString("title", recipeSummaryTitle);
                bundleForRecipeSummary.putString("ingredients", recipeSummaryIngredients);
                Intent intent = new Intent(context, UserRecipeSummaryActivity.class);
                intent.putExtras(bundleForRecipeSummary);
                context.startActivity(intent);
            }
        });
        return new UserRecipesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecipesViewHolder holder, int position) {
        UserRecipes currentRecipe = userRecipes.get(position);
        holder.textViewTitle.setText(currentRecipe.getRecipeName());
        holder.textViewDescription.setText(currentRecipe.getRecipeIngredients());

    }

    @Override
    public int getItemCount() {
        return userRecipes.size();
    }

    public void setUserRecipe(List<UserRecipes> userRecipe){
        this.userRecipes = userRecipe;
        notifyDataSetChanged();
    }

    class UserRecipesViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        public UserRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.userRecipeTitle);
            textViewDescription= itemView.findViewById(R.id.userRecipeIngredient);
        }
    }
}
