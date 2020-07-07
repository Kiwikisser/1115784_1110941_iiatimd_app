package com.example.koffie_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CoffeeDAO {

    @Query("SELECT * FROM coffee")
    Coffee[] getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertCoffee(Coffee coffee);

    @Delete
    void deleteCoffee(Coffee coffee);
}
