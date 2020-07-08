package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeCreateActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final String POSTURL = "http://192.168.178.115:8000/api/recipes/create"; // path to store recipes
    private String coffeebean; // variable for dropdown spinner
    AppRoomDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_create);
        db = AppRoomDatabase.getInstance(getApplicationContext());
        Button submitRecipeButton = findViewById(R.id.button_submit_recipe);
        submitRecipeButton.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.input_coffeeBean);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffeeTypes, android.R.layout.simple_spinner_item); //evt get coffetypes array from room coffee data
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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

    // Get recipe data from form and check if any of them are empty, returns object with field data only if all fields aren't empty
    private JSONObject getRecipeFormData(){
        EditText recipeNameField = findViewById(R.id.input_recipeName);
        EditText recipeIngredientField = findViewById(R.id.input_ingredients);
        EditText recipeServingsField = findViewById(R.id.input_coffeeServings);
        EditText recipePrepTimeField = findViewById(R.id.input_prepTime);

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

        String recipeName = recipeNameField.getText().toString();
        String recipeIngredients = recipeIngredientField.getText().toString();
        Integer recipeServings = Integer.parseInt(recipeServingsField.getText().toString());
        String recipePrepTime = recipePrepTimeField.getText().toString();
        JSONObject recipeObject = new JSONObject();
        try{
            recipeObject.put("recipe_name", recipeName);
            recipeObject.put("recipe_ingredients", recipeIngredients);
            recipeObject.put("coffee_bean", coffeebean);
            recipeObject.put("coffee_servings", recipeServings);
            recipeObject.put("coffee_prep_time", recipePrepTime);
        }
        catch(Exception e){}
        return recipeObject;
    }

    private void createUserRecipesForRoom(){} // evt set post object in this function and return it to the thread

    @Override
    public void onClick(View v) {
        JSONObject newRecipeData = getRecipeFormData();
        if(newRecipeData != null){
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POSTURL, newRecipeData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("response", response.toString());

                    //start db, evt set related code into one function

                    try {
                        String recipe_id = response.getString("recipe_id");
                        String username = response.getString("user_name");
                        String recipe_name = response.getString("recipe_name");
                        String recipe_ingredients = response.getString("recipe_ingredients");
                        String coffee_bean = response.getString("coffee_bean");
                        int coffee_servings = response.getInt("coffee_servings");
                        int coffee_prep_time = response.getInt("coffee_prep_time");
                        UserRecipes userRecipes = new UserRecipes(recipe_id, username, recipe_name, recipe_ingredients, coffee_bean, coffee_servings, coffee_prep_time);

                        // start thread to insert response values
                        new Thread(new InsertNewUserRecipe(db,userRecipes)).start();
                    } catch (JSONException e) {}

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.getMessage());
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

            // evt redirect to recipe overview or success page
            Intent toIntroductionScreen = new Intent(this, MainActivity.class);
            startActivity(toIntroductionScreen);
        }
    }
}
