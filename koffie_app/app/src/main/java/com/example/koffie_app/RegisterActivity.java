package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final String POSTURL_MAUR = "http://192.168.178.115:8000/api/register";
    private final String POSTURL_AMOS = "http://192.168.2.6:8000/api/register";
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_register);

        editTextUsername = findViewById(R.id.register_username);
        editTextEmail = findViewById(R.id.register_email);
        editTextPassword = findViewById(R.id.register_password);
        editTextPasswordConf = findViewById(R.id.register_password_confirm);

        Button startIntroductionButton = findViewById(R.id.register_submit);
        startIntroductionButton.setOnClickListener(this);
    }

    private JSONObject getRegisterFormData(String username, String email, String password){
//        String username = editTextUsername.getText().toString();
//        String email = editTextEmail.getText().toString();
//        String password = editTextPassword.getText().toString();

        JSONObject userObject = new JSONObject();
        try{
            userObject.put("name", username);
            userObject.put("email", email);
            userObject.put("password", password);
        }
        catch(Exception e){return null;}
        return userObject;
    }

    private void setCredentials(String username, String email, String password){
        UserAuthentication.getInstance(this).setUsername(username);
        UserAuthentication.getInstance(this).setEmail(email);
        UserAuthentication.getInstance(this).setPassword(password);
    }

    private void storeUserInPrefs(User userObj){
        UserAuthentication.getInstance(this).storeInSharedPrefs(userObj);
    }

    private User retrieveFromPrefs(String key){
        return UserAuthentication.getInstance(this).retrieveFromSharedPrefs(key);
    }

    private void assignToken(String token){
        UserAuthentication.getInstance(this).setToken(token);
    }

    private void logIn(){
        UserAuthentication.getInstance(this).setAuthenticated(true);
    }

    @Override
    public void onClick(View v) {
        final String username = editTextUsername.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String passwordConf = editTextPasswordConf.getText().toString();

        Log.d("register", "onClick: ");
        Log.d("username: ", username);
        Log.d("email: ", email);
        Log.d("password: ", password);
        Log.d("passwordConf: ", passwordConf);
        Log.d("pass match: ", String.valueOf((password.equals(passwordConf))));

        if (password.equals(passwordConf)){
            JSONObject newUserData = getRegisterFormData(username, email, password);
            Log.d("data json: ", String.valueOf(newUserData));
            if(newUserData != null){
                RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POSTURL_AMOS, newUserData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());

                        try {
                            String token = response.getString("access_token");

                            setCredentials(username, email, password);
                            assignToken(token);
                            logIn();
                            storeUserInPrefs(new User(username, email, password));
//                            retrieveFromPrefs("userData");
                        } catch (JSONException e) {
                            Log.d("onResponse: ", String.valueOf(e));
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", String.valueOf(error));
                    }
                });

                VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

                // TEMPORARY REDIRECT TO MAIN ACTIVITY
                Intent toIntroductionScreen = new Intent(this, MainActivity.class);
                startActivity(toIntroductionScreen);

                // TODO: REDIRECT TO HOME
            } else {
                Toast.makeText(this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}


