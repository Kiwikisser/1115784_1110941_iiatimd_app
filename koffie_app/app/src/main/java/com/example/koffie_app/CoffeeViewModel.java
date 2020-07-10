package com.example.koffie_app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CoffeeViewModel extends AndroidViewModel {
    private CoffeeRepository repository;
    private LiveData<List<Coffee>> allCoffee;

    public CoffeeViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeRepository(application);
        allCoffee = repository.getAllCoffee();
    }

    public void insert(Coffee coffee){
        //Log.d("insert: ", coffee.getName());
        repository.insert(coffee);
    }

    public void update(Coffee coffee){
        repository.update(coffee);
    }

    public void delete(Coffee coffee){
        repository.delete(coffee);
    }

    public void deleteAllCoffee(){
        repository.deleteAllCoffee();
    }

    public LiveData<List<Coffee>> getAllCoffee(){
        return allCoffee;
    }
}
