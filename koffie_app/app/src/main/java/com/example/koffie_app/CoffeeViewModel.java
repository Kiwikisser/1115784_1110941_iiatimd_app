package com.example.koffie_app;

import android.app.Application;

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
