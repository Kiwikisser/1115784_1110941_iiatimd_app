package com.example.koffie_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Coffee.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract CoffeeDAO coffeeDAO();

    public static AppRoomDatabase instance;

    //singleton
    static synchronized AppRoomDatabase getInstance(Context context){
        if (instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static AppRoomDatabase create(final Context context){
        return Room.databaseBuilder(context, AppRoomDatabase.class, "coffee").fallbackToDestructiveMigration().build();
    }
}
