package com.example.koffie_app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserAuthentication extends AndroidViewModel {

//    private static UserAuthentication instance;
//    private Context context;
//
//    private String username;
//    private String email;
//    private String password;
//    private String token;
//    private boolean isAuthenticated;

    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserAuthentication(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();        // not required for anything yet.
    }

//    private UserAuthentication(Context ctx) {
//        this.context = ctx;
//    }
//
//    //singleton
//    public static synchronized UserAuthentication getInstance(Context context){
//        if (instance == null){
//            instance = new UserAuthentication(context);
//        }
//        return instance;
//    }

    public void insert(User user){
        repository.insert(user);
    }

    public void update(User user){
        repository.update(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteAllUsers(){
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public void setAuthenticated(boolean authenticated) {
//        isAuthenticated = authenticated;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public boolean isAuthenticated() {
//        return isAuthenticated;
//    }
}
