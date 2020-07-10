package com.example.koffie_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRecipesRepository {
    private UserRecipesDAO userRecipesDAO;
    private LiveData<List<UserRecipes>> allRecipes;

    public UserRecipesRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getInstance(application);
        userRecipesDAO = database.userRecipesDAO();
        allRecipes = userRecipesDAO.getAllRecipes();
    }

    public void insert(UserRecipes userRecipes){
        new InsertUserRecipesAsyncTask(userRecipesDAO).execute(userRecipes);
    }

    public void update(UserRecipes userRecipes){
        new UpdateUserRecipesAsyncTask(userRecipesDAO).execute(userRecipes);
    }

    public void delete(UserRecipes userRecipes){
        new DeleteUserRecipesAsyncTask(userRecipesDAO).execute(userRecipes);
    }

    public void deleteAllUserRecipes(){
        new DeleteAllUserRecipesAsyncTask(userRecipesDAO).execute();
    }

    public LiveData<List<UserRecipes>> getAllRecipes() {
        return allRecipes;
    }

    private static class InsertUserRecipesAsyncTask extends AsyncTask<UserRecipes, Void, Void> {
        private UserRecipesDAO userRecipesDAO;

        private InsertUserRecipesAsyncTask(UserRecipesDAO userRecipesDAO){
            this.userRecipesDAO = userRecipesDAO;
        }

        @Override
        protected Void doInBackground(UserRecipes... userRecipes) {
            userRecipesDAO.insert(userRecipes[0]);
            return null;
        }
    }

    private static class UpdateUserRecipesAsyncTask extends AsyncTask<UserRecipes, Void, Void>{
        private UserRecipesDAO userRecipesDAO;

        private UpdateUserRecipesAsyncTask(UserRecipesDAO userRecipesDAO){
            this.userRecipesDAO = userRecipesDAO;
        }

        @Override
        protected Void doInBackground(UserRecipes... userRecipes) {
            userRecipesDAO.update(userRecipes[0]);
            return null;
        }
    }

    private static class DeleteUserRecipesAsyncTask extends AsyncTask<UserRecipes, Void, Void>{
        private UserRecipesDAO userRecipesDAO;

        private DeleteUserRecipesAsyncTask(UserRecipesDAO userRecipesDAO){
            this.userRecipesDAO = userRecipesDAO;
        }

        @Override
        protected Void doInBackground(UserRecipes... userRecipes) {
            userRecipesDAO.delete(userRecipes[0]);
            return null;
        }
    }

    private static class DeleteAllUserRecipesAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserRecipesDAO userRecipesDAO;

        private DeleteAllUserRecipesAsyncTask(UserRecipesDAO userRecipesDAO){
            this.userRecipesDAO = userRecipesDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userRecipesDAO.deleteAllUserRecipes();
            return null;
        }
    }
}