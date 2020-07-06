package com.example.koffie_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CoffeeDAO {

    @Query("SELECT * FROM coffee")
    List<Coffee> getAll();

    @Insert
    void InsertCoffee(Coffee coffee);

    @Delete
    void delete(Coffee coffee);
}
