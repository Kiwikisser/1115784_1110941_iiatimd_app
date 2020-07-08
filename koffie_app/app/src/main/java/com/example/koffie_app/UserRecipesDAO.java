package com.example.koffie_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface UserRecipesDAO {
    @Query("SELECT * FROM userRecipes")
    List<UserRecipes> getAll();

    @Insert
    void InsertRecipe(UserRecipes userRecipes);

    @Delete
    void delete(UserRecipes userRecipes);
}
