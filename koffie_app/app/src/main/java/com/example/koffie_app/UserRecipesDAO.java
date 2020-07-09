package com.example.koffie_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserRecipesDAO {
    @Query("SELECT * FROM user_recipes")
    LiveData<List<UserRecipes>> getAllRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserRecipes userRecipes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(UserRecipes userRecipes);

    @Delete
    void delete(UserRecipes userRecipes);

    @Query("DELETE FROM USER_RECIPES")
    void deleteAllUserRecipes();
}
