package com.example.koffie_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Coffee.class}, version = 7)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract CoffeeDAO coffeeDAO();

    public static AppRoomDatabase instance;

    //singleton

    public static synchronized AppRoomDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppRoomDatabase.class, "coffee_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static AppRoomDatabase.Callback roomCallback = new AppRoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PupulateDbAsyncTask(instance).execute();
        }
    };

    private static class PupulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CoffeeDAO coffeeDao;

        private PupulateDbAsyncTask(AppRoomDatabase db){
            coffeeDao = db.coffeeDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //can insert records here
//            coffeeDao.insert(new Coffee("black", "super black coffee", 1));
//            coffeeDao.insert(new Coffee("white", "super white coffee", 2));
//            coffeeDao.insert(new Coffee("green", "super green coffee", 3));
            return null;
        }
    }

//    static synchronized AppRoomDatabase getInstance(Context context){
//        if (instance == null){
//            instance = create(context);
//        }
//        return instance;
//    }
//
//    private static AppRoomDatabase create(final Context context){
//        return Room.databaseBuilder(context, AppRoomDatabase.class, "coffee_database").fallbackToDestructiveMigration().build();
//    }
}
