package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AppLastIntroductionActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogue.LoginDialogueListener{

    private String POSTURL = "http://192.168.2.6:8000/api/login";
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_introduction_final);

        Button createAccIntroductionButton = findViewById(R.id.button_intro_createAccount);
        Button loginIntroductionButton = findViewById(R.id.button_intro_login);
        Button skipToHPIntroductionButton = findViewById(R.id.button_intro_skip);

        createAccIntroductionButton.setOnClickListener(this);
        loginIntroductionButton.setOnClickListener(this);
        skipToHPIntroductionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_intro_createAccount:
                Intent toRegisterScreen = new Intent(this, RegisterActivity.class);
                startActivity(toRegisterScreen);
                break;
            case R.id.button_intro_login:
//                Intent toLoginScreen = new Intent(this, LoginActivity.class);
//                startActivity(toLoginScreen);
                LoginDialogue loginDialogue = new LoginDialogue();
                loginDialogue.show(getSupportFragmentManager(), "login dialog");
                break;
            case R.id.button_intro_skip:
                Intent toHomeScreen = new Intent(this, AppHomePageActivity.class); //evt to homepage activity
                startActivity(toHomeScreen);
                break;
        }
    }

    @Override
    public void retrieveTexts(final String email, final String pword) {

        JSONObject userData = getLoginData(email, pword);

        final boolean[] successful = {false};

        if(userData != null){
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POSTURL, userData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("response", response.toString());

                    //start db, evt set related code into one function

                    try {
                        token = response.getString("access_token");

                    } catch (JSONException e) {}
//                    Log.d("istokennull?: ", String.valueOf(token) );

                    AuthenticateUser(email, pword, token, true);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", String.valueOf(error));
                }
            });

            if (token != null){
            }

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

            // evt redirect to recipe overview or success page
            Intent toIntroductionScreen = new Intent(this, MainActivity.class);
            startActivity(toIntroductionScreen);
        }




//        // call login instance
//        String username = UserAuthentication.getInstance(this).getUsername();
//        String password = UserAuthentication.getInstance(this).getPassword();
//
//        if (username.equals(email) && password.equals(pword)){
//            // TODO: RETURN USER TO HOME
//            Intent toMainScreen = new Intent(this, MainActivity.class);
//            startActivity(toMainScreen);
//        }
    }

    private JSONObject getLoginData(String email, String password){

//        String recipeName = recipeNameField.getText().toString();
//        String recipeIngredients = recipeIngredientField.getText().toString();

        JSONObject recipeObject = new JSONObject();
        try{
            recipeObject.put("email", email);
            recipeObject.put("password", password);
        }
        catch(Exception e){}
        return recipeObject;
    }

    private void AuthenticateUser(String email, String password, String token, boolean isAuth){
        UserAuthentication.getInstance(this).setEmail(email);
        UserAuthentication.getInstance(this).setPassword(password);
        UserAuthentication.getInstance(this).setToken(token);
        UserAuthentication.getInstance(this).setAuthenticated(isAuth);
    }
}
