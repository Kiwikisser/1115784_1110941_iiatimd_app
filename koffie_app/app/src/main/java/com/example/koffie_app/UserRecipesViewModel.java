package com.example.koffie_app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRecipesViewModel extends AndroidViewModel {
    private UserRecipesRepository repository;
    private LiveData<List<UserRecipes>> allRecipes;

    public UserRecipesViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRecipesRepository(application);
        allRecipes = repository.getAllRecipes();
    }

    public void insert(UserRecipes userRecipes){
        repository.insert(userRecipes);
    }

    public void update(UserRecipes userRecipes){
        repository.update(userRecipes);
    }

    public void delete(UserRecipes userRecipes){
        repository.delete(userRecipes);
    }

    public void deleteAllRecipes(){
        repository.deleteAllUserRecipes();
    }

    public LiveData<List<UserRecipes>> getAllRecipes(){
        return allRecipes;
    }
}