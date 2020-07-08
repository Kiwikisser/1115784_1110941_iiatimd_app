package com.example.koffie_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CoffeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Coffee coffee);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Coffee coffee);

    @Delete
    void delete(Coffee coffee);

    @Query("DELETE FROM coffee_table")
    void deleteAllCoffee();

    @Query("SELECT * FROM coffee_table")
    LiveData<List<Coffee>> getAllCoffee();

//    @Query("SELECT * FROM coffee")
//    Coffee[] getAll();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void InsertCoffee(Coffee coffee);
//
//    @Delete
//    void deleteCoffee(Coffee coffee);
}
