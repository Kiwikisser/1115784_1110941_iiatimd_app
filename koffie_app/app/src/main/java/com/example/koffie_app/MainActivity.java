package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.Handler;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogue.LoginDialogueListener {

    AppRoomDatabase database;
    private CoffeeViewModel coffeeViewModel;
    SharedPreferences prefs = null;
    Intent logButtonIntent;
    private TextView textViewMainHeader;
    private String POSTURL = "https://still-atoll-19210.herokuapp.com/api/login";
    private String token = null;
    private String LOGOUTURL = "https://still-atoll-19210.herokuapp.com/api/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.l_main_page_stime);
        textViewMainHeader = this.findViewById(R.id.mainpage_header);

        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        UserAuthentication.getInstance(this).setmPrefs(mPrefs);

        //Log.d("Welcome: ",UserAuthentication.getInstance(this).retrieveFromSharedPrefs("userData").getUsername() );

        if(!UserAuthentication.getInstance(this).isAuthenticated()){ // User is not logged in
            Button redirectToLoginButton = findViewById(R.id.button_mainpage_log);
            redirectToLoginButton.setOnClickListener(this);
            // set intent to redirect to login page
            textViewMainHeader.setText("Is not logged in");
            redirectToLoginButton.setText("login");
            redirectToLoginButton.setBackgroundColor(Color.parseColor("#526C46"));

            logButtonIntent = new Intent(this, MainActivity.class);
        }
        else{ // User is logged in
            Button startLogoutButton = findViewById(R.id.button_mainpage_log);
            startLogoutButton.setOnClickListener(this);
            String username = UserAuthentication.getInstance(this).retrieveFromSharedPrefs("userData").getUsername();
            textViewMainHeader.setText("Welcome back " + username + "!"); // second value is username

            //do something for logout
            logButtonIntent = new Intent(this, MainActivity.class);
        }
        Button redirectToHomePage = findViewById(R.id.button_startHome);
        redirectToHomePage.setOnClickListener(this);

        Button redirectToIntroduction = findViewById(R.id.button_mainpage_introduction);
        redirectToIntroduction.setOnClickListener(this);

        coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
        Handler handler = new Handler();
        int connectionIntverval = 20000;
        handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, coffeeViewModel));

        prefs = getSharedPreferences("com.example.koffie_app", MODE_PRIVATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            setContentView(R.layout.l_main_page);
            Button startIntroductionButton = findViewById(R.id.button_startIntroduction);
            startIntroductionButton.setOnClickListener(this);

            coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
            Handler handler = new Handler();
            int connectionIntverval = 20000;
            handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, coffeeViewModel));

            prefs.edit().putBoolean("firstrun", false).commit();
        }
        // TODO: remove this test account
        UserAuthentication.getInstance(this).setUsername("a");
        UserAuthentication.getInstance(this).setPassword("2222");
//        UserAuthentication.getInstance(this).isAuthenticated();

    }
    public void onClick(View v){
//        Intent toHomePage = new Intent(this, AppHomePageActivity.class);
//        startActivity(toHomePage);
        switch(v.getId()){
            case R.id.button_startIntroduction:
                Intent toIntroductionScreen = new Intent(this, AppIntroductionActivity.class);
                startActivity(toIntroductionScreen);
                break;
            case R.id.button_startHome:
                Intent toHomePage = new Intent(this, AppHomePageActivity.class);
                startActivity(toHomePage);
                break;
            case R.id.button_mainpage_log:
                boolean loginState = UserAuthentication.getInstance(this).isAuthenticated();
                logUserInOrOut(loginState, logButtonIntent);
                break;
            case R.id.button_mainpage_introduction:
                Intent toIntroductionAgain = new Intent(this, AppIntroductionActivity.class);
                startActivity(toIntroductionAgain);
                break;
        }
    }

    public void logUserInOrOut(boolean state, Intent intent){
        if (state) {
            //logout
            String userToken = UserAuthentication.getInstance(this).getToken();
            logOutUser();
        } else {
            //login
            LoginDialogue loginDialogue = new LoginDialogue();
            loginDialogue.show(getSupportFragmentManager(), "login dialog");
        }
    }

    private JSONObject getLoginData(String email, String password){
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

    @Override
    public void retrieveTexts(final String email, final String pword) {

        JSONObject userData = getLoginData(email, pword);

        final Context ctx = this;

        if(userData != null){
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POSTURL, userData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("response", response.toString());

                    try {
                        token = response.getString("access_token");

                    } catch (JSONException e) {}

                    AuthenticateUser(email, pword, token, true);

                    Intent toIntroductionScreen = new Intent(ctx, MainActivity.class);
                    startActivity(toIntroductionScreen);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", String.valueOf(error));
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        }
    }

    private String getToken(){
        return UserAuthentication.getInstance(this).getToken();
    }

    public void logOutUser(){
        final Context ctx = this;
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LOGOUTURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

//                    try {
//                        token = response.getString("access_token");
//
//                    } catch (JSONException e) {}

                UserAuthentication.getInstance(ctx).setAuthenticated(false);

                Intent toIntroductionScreen = new Intent(ctx, MainActivity.class);
                startActivity(toIntroductionScreen);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", String.valueOf(error));
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                String authValue = "Bearer " + getToken();
                params.put("Authorization", authValue);
                params.put("Accept", "application/json; charset=UTF-8");
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}