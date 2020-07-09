package com.example.koffie_app;

import android.content.Context;

public class UserAuthentication {

    private static UserAuthentication instance;
    private Context context;

    private String username;
    private String email;
    private String password;
    private String token;
    private boolean isAuthenticated;

    private UserAuthentication(Context ctx) {
        this.context = ctx;
    }

    //singleton
    public static synchronized UserAuthentication getInstance(Context context){
        if (instance == null){
            instance = new UserAuthentication(context);
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
