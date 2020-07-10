package com.example.koffie_app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

import java.util.UUID;

import static java.lang.Integer.parseInt;

public class RecipeEditActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final String POSTURL = "https://still-atoll-19210.herokuapp.com/api/recipes/update"; // path to store recipes, evt laravel online gooien ergens?
    private String coffeebean; // variable for dropdown spinner value in form
    private UserRecipesViewModel userRecipesViewModel;
    AppRoomDatabase db;

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close?\nYour changes will be lost").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                RecipeEditActivity.this.finish();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_edit);

        Spinner spinner = findViewById(R.id.input_recipe_edit_coffeeBean);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffeeTypes, android.R.layout.simple_spinner_item); //evt get coffetypes array from room coffee data
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        db = AppRoomDatabase.getInstance(getApplicationContext());
        userRecipesViewModel = ViewModelProviders.of(this).get(UserRecipesViewModel.class);

        Button submitRecipeButton = findViewById(R.id.button_submit_recipe);
        submitRecipeButton.setOnClickListener(this);

        Bundle recipeSummaryData = getIntent().getExtras();
        TextView editFormHeader = findViewById(R.id.recipe_edit_header);
        EditText editRecipeName = findViewById(R.id.input_recipe_edit_name);
        EditText editRecipeIngredients = findViewById(R.id.input_recipe_edit_ingredients);
        EditText editRecipeServings = findViewById(R.id.input_recipe_edit_coffeeServings);
        EditText editRecipePrepTime = findViewById(R.id.input_recipe_edit_prepTime);
        String recipeName = "Editing " + recipeSummaryData.getString("title");
        editFormHeader.setText(recipeName);
        editRecipeName.setText(recipeSummaryData.getString("title"));
        editRecipeIngredients.setText(recipeSummaryData.getString("ingredients"));

        int selectionPosition = adapter.getPosition(recipeSummaryData.getString("coffee_bean"));
        spinner.setSelection(selectionPosition);

        editRecipeServings.setText(recipeSummaryData.getString("servings"));
        editRecipePrepTime.setText(recipeSummaryData.getString("prep_time"));

    }

    // Spinner Dropdown Methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        coffeebean = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    // Gets recipe data from form. Returns object with inputfield data only if all fields aren't empty
    private JSONObject getRecipeFormData(){
        EditText recipeNameField = findViewById(R.id.input_recipe_edit_name);
        EditText recipeIngredientField = findViewById(R.id.input_recipe_edit_ingredients);
        EditText recipeServingsField = findViewById(R.id.input_recipe_edit_coffeeServings);
        EditText recipePrepTimeField = findViewById(R.id.input_recipe_edit_prepTime);

        if( TextUtils.isEmpty(recipeNameField.getText())){
            recipeNameField.setError( "Recipe name is required!");
            return null;
        }
        if( TextUtils.isEmpty(recipeIngredientField.getText())){
            recipeIngredientField.setError( "Ingredients field is empty!");
            return null;
        }
        if( TextUtils.isEmpty(recipeServingsField.getText())){
            recipeServingsField.setError( "Value required!");
            return null;
        }
        if( TextUtils.isEmpty(recipePrepTimeField.getText())){
            recipePrepTimeField.setError( "Value required!");
            return null;
        }
        Bundle recipeSummaryData = getIntent().getExtras();
        String recipeId = recipeSummaryData.getString("recipe_id");
        String currentUsername = "tito"; // example, evt username getten && default username if user is not logged in
        String recipeName = recipeNameField.getText().toString();
        String recipeIngredients = recipeIngredientField.getText().toString();
        Integer recipeServings = parseInt(recipeServingsField.getText().toString());
        String recipePrepTime = recipePrepTimeField.getText().toString();
        JSONObject recipeObject = new JSONObject();
        try{
            recipeObject.put("recipeId", recipeId);
            recipeObject.put("user_name", currentUsername);
            recipeObject.put("recipe_name", recipeName);
            recipeObject.put("recipe_ingredients", recipeIngredients);
            recipeObject.put("coffee_bean", coffeebean);
            recipeObject.put("coffee_servings", recipeServings);
            recipeObject.put("coffee_prep_time", recipePrepTime);
        }
        catch(Exception e){}
        Log.d("sentObject", recipeObject.toString());
        return recipeObject;
    }

    @Override
    public void onClick(View v) {
        final JSONObject newRecipeData = getRecipeFormData();
        if(newRecipeData != null){
            
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POSTURL, newRecipeData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String recipe_id = response.getString("recipe_id");
                        String username = response.getString("user_name");
                        String recipe_name = response.getString("recipe_name");
                        String recipe_ingredients = response.getString("recipe_ingredients");
                        String coffee_bean = response.getString("coffee_bean");
                        int coffee_servings = response.getInt("coffee_servings");
                        int coffee_prep_time = response.getInt("coffee_prep_time");
                        UserRecipes userRecipes = new UserRecipes(recipe_id, username, recipe_name, recipe_ingredients, coffee_bean, coffee_servings, coffee_prep_time);
                        // instead of inserting user recipe value UPDATE existing user recipe
                        userRecipesViewModel.insert(userRecipes);
                    } catch (JSONException e) {}

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        // no connection, locally edit recipe to room
                        UserRecipes userRecipes = new UserRecipes(newRecipeData.getString("recipeId"), newRecipeData.getString("user_name"), newRecipeData.getString("recipe_name"), newRecipeData.getString("recipe_ingredients"),
                        newRecipeData.getString("coffee_bean"), newRecipeData.getInt("coffee_servings"), newRecipeData.getInt("coffee_prep_time"));
                        userRecipesViewModel.insert(userRecipes);
                        // as soon as internet connection is made, send this offline action thats stored in room to the live database.  id doesnt need to be generated by laravel if this is the case.

                    } catch (JSONException e) {}
                }
            });
            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
            userRecipesViewModel.getAllRecipes();
            Intent toIntroductionScreen = new Intent(this, UserRecipesOverviewActivity.class);
            startActivity(toIntroductionScreen);
        }
    }
}
