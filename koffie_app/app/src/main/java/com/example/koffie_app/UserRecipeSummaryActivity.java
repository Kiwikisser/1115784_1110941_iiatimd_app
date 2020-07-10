package com.example.koffie_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

public class UserRecipeSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewTitle;
    private TextView textViewCoffeeBean;
    private TextView textViewIngredients;
    private TextView textViewCoffeeServings;
    private TextView textViewCoffeePrepTime;
    final String DELETEPOSTURL = "http://192.168.178.115:8000/api/recipes/delete";
    private UserRecipesViewModel userRecipesViewModel;
    AppRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_summary);
        Bundle recipeCardViewData = getIntent().getExtras();
        db = AppRoomDatabase.getInstance(getApplicationContext());
        textViewTitle = this.findViewById(R.id.recipe_summary_title);
        textViewCoffeeBean = this.findViewById(R.id.recipe_summary_coffeebean);
        textViewIngredients= this.findViewById(R.id.recipe_summary_ingredients);
        textViewCoffeeServings = this.findViewById(R.id.recipe_summary_servings);
        textViewCoffeePrepTime = this.findViewById(R.id.recipe_summary_preptime);

        textViewTitle.setText(recipeCardViewData.getString("title"));
        textViewCoffeeBean.setText(recipeCardViewData.getString("coffee_bean"));
        textViewIngredients.setText(recipeCardViewData.getString("ingredients"));
        textViewCoffeeServings.setText(recipeCardViewData.getString("servings"));
        textViewCoffeePrepTime.setText(recipeCardViewData.getString("prep_time"));

        Button editRecipeButton = findViewById(R.id.button_edit_recipe);
        editRecipeButton.setOnClickListener(this);

        Button deleteRecipeButton = findViewById(R.id.button_delete_recipe);
        deleteRecipeButton.setOnClickListener(this);
    }
    public void onClick(View v){
        final Bundle recipeCardViewData = getIntent().getExtras();

        switch(v.getId()) {
            case R.id.button_edit_recipe:
                Bundle bundleForRecipeEditForm = new Bundle();
                bundleForRecipeEditForm.putString("title", recipeCardViewData.getString("title"));
                bundleForRecipeEditForm.putString("recipe_id", recipeCardViewData.getString("recipe_id"));
                bundleForRecipeEditForm.putString("ingredients", recipeCardViewData.getString("ingredients"));
                bundleForRecipeEditForm.putString("coffee_bean", recipeCardViewData.getString("coffee_bean"));
                bundleForRecipeEditForm.putString("servings", recipeCardViewData.getString("servings"));
                bundleForRecipeEditForm.putString("prep_time", recipeCardViewData.getString("prep_time"));

                Intent toEditRecipeForm = new Intent(this, RecipeEditActivity.class); // evt redirect to edit activity
                toEditRecipeForm.putExtras(bundleForRecipeEditForm);
                startActivity(toEditRecipeForm);
                break;

            case R.id.button_delete_recipe:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to delete your recipe?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        JSONObject toDeleteObject = new JSONObject();
                        try { toDeleteObject.put("recipeId",recipeCardViewData.getString("recipe_id")); } catch (JSONException e) {}
                        RequestQueue queue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
                        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, DELETEPOSTURL, toDeleteObject ,new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {}
                        }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){}
                        });
                        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                        new Thread(new DeleteRecipeActivity(db,recipeCardViewData.getString("recipe_id"))).start();
                        Intent backToRecipesOverview = new Intent(getApplicationContext(),UserRecipesOverviewActivity.class);
                        startActivity(backToRecipesOverview);
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
    }
}
