package com.example.koffie_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CoffeeRepository {
    private CoffeeDAO coffeeDAO;
    private LiveData<List<Coffee>> allCoffee;

    public CoffeeRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getInstance(application);
        coffeeDAO = database.coffeeDAO();
        allCoffee = coffeeDAO.getAllCoffee();
    }

    public void insert(Coffee coffee){
        new InsertCoffeeAsyncTask(coffeeDAO).execute(coffee);
    }

    public void update(Coffee coffee){
        new UpdateCoffeeAsyncTask(coffeeDAO).execute(coffee);
    }

    public void delete(Coffee coffee){
        new DeleteCoffeeAsyncTask(coffeeDAO).execute(coffee);
    }

    public void deleteAllCoffee(){
        new DeleteAllCoffeeAsyncTask(coffeeDAO).execute();
    }

    public LiveData<List<Coffee>> getAllCoffee() {
        return allCoffee;
    }

    private static class InsertCoffeeAsyncTask extends AsyncTask<Coffee, Void, Void> {
        private CoffeeDAO coffeeDao;

        private InsertCoffeeAsyncTask(CoffeeDAO coffeeDao){
            this.coffeeDao = coffeeDao;
        }

        @Override
        protected Void doInBackground(Coffee... coffees) {
            coffeeDao.insert(coffees[0]);
            return null;
        }
    }

    private static class UpdateCoffeeAsyncTask extends AsyncTask<Coffee, Void, Void>{
        private CoffeeDAO coffeeDao;

        private UpdateCoffeeAsyncTask(CoffeeDAO coffeeDao){
            this.coffeeDao = coffeeDao;
        }

        @Override
        protected Void doInBackground(Coffee... coffees) {
            coffeeDao.update(coffees[0]);
            return null;
        }
    }

    private static class DeleteCoffeeAsyncTask extends AsyncTask<Coffee, Void, Void>{
        private CoffeeDAO coffeeDao;

        private DeleteCoffeeAsyncTask(CoffeeDAO coffeeDao){
            this.coffeeDao = coffeeDao;
        }

        @Override
        protected Void doInBackground(Coffee... coffees) {
            coffeeDao.delete(coffees[0]);
            return null;
        }
    }

    private static class DeleteAllCoffeeAsyncTask extends AsyncTask<Void, Void, Void>{
        private CoffeeDAO coffeeDao;

        private DeleteAllCoffeeAsyncTask(CoffeeDAO coffeeDao){
            this.coffeeDao = coffeeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            coffeeDao.deleteAllCoffee();
            return null;
        }
    }
}
