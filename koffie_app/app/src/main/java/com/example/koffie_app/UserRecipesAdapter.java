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
                TextView textViewCoffeeBean= v.findViewById(R.id.userRecipeCoffeeBean);
                TextView textViewIngredients= v.findViewById(R.id.userRecipeIngredient);
                TextView textViewServings= v.findViewById(R.id.userRecipeServings);
                TextView textViewPrepTime= v.findViewById(R.id.userRecipePrepTime);
                TextView textViewRecipeId= v.findViewById(R.id.userRecipeId);

                Bundle bundleForRecipeSummary = new Bundle();
                bundleForRecipeSummary.putString("title", textViewTitle.getText().toString());
                bundleForRecipeSummary.putString("ingredients", textViewCoffeeBean.getText().toString());
                bundleForRecipeSummary.putString("coffee_bean", textViewIngredients.getText().toString());
                bundleForRecipeSummary.putString("servings", textViewServings.getText().toString());
                bundleForRecipeSummary.putString("prep_time", textViewPrepTime.getText().toString());
                bundleForRecipeSummary.putString("recipe_id", textViewRecipeId.getText().toString());

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
        holder.textViewIngredients.setText(currentRecipe.getRecipeIngredients());
        holder.textViewCoffeeBean.setText(currentRecipe.getCoffeeBean());
        holder.textViewCoffeeServings.setText(String.valueOf(currentRecipe.getCoffeeServings()));
        holder.textViewPrepTime.setText(String.valueOf(currentRecipe.getCoffeePrepTime()));
        holder.textViewRecipeId.setText(currentRecipe.getRecipeId());
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
        private TextView textViewIngredients;
        private TextView textViewCoffeeBean;
        private TextView textViewCoffeeServings;
        private TextView textViewPrepTime;
        private TextView textViewRecipeId;
        public UserRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.userRecipeTitle);
            textViewIngredients= itemView.findViewById(R.id.userRecipeIngredient);
            textViewCoffeeBean= itemView.findViewById(R.id.userRecipeCoffeeBean);
            textViewCoffeeServings= itemView.findViewById(R.id.userRecipeServings);
            textViewPrepTime= itemView.findViewById(R.id.userRecipePrepTime);
            textViewRecipeId= itemView.findViewById(R.id.userRecipeId);
        }
    }
}
