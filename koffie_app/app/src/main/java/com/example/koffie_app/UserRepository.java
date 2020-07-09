package com.example.koffie_app;

import android.app.Application;
import android.os.AsyncTask;
import android.text.SpanWatcher;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository implements AsyncResponse{
    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;
    private User loginUser;

    public UserRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getInstance(application);
        userDAO = database.userDAO();
        allUsers = userDAO.getAllUsers();       // not required for anything yet.
    }

    public void insert(User user){
        Log.d( "inserted: ", String.valueOf(user));
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDAO).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllUsersAsyncTask(userDAO).execute();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public User findUserByNameAndPass(String uname, String pword) throws ExecutionException, InterruptedException {
        Log.d("findUser before: ", String.valueOf(loginUser));
        MyAsyncTask asyncTask =new MyAsyncTask(userDAO, uname, pword);
        asyncTask.delegate = this;
        User user = asyncTask.execute().get();
        Log.d("async: ", "executed");
        return user;
    }

    @Override
    public void processFinish(User output) {
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        Log.d("processFinish: ", "output " + output.getUsername());
        loginUser = output;
        Log.d("findUser after: ", String.valueOf(loginUser));
    }

    public class MyAsyncTask extends AsyncTask<User, Void, User> {
        public AsyncResponse delegate = null;
        private UserDAO userDAO;
        private String username;
        private String password;

        private MyAsyncTask(UserDAO userDAO, String uname, String pword){
            this.userDAO = userDAO;
            this.username = uname;
            this.password = pword;
        }

        @Override
        protected User doInBackground(User... users) {
            User user = userDAO.findUserByNameAndPass(username, password);
            return user;
        }

        @Override
        protected void onPostExecute(User result) {
            // query here?
            delegate.processFinish(result);
        }
    }

//    public User findUserByNameAndPass(){
////        new FindUserAsyncTask(userDAO).execute();
////        return loginUser;
//        FindUserAsyncTask findUserAsyncTask = (FindUserAsyncTask) new FindUserAsyncTask(userDAO, new FindUserAsyncTask.UserAsyncResponse(){
//            @Override
//            public void processFinish(User output) {
//                loginUser = output;
//            }
//        }, loginUser).execute();
//        return loginUser;
//    }

//    public User findUserByNameAndPass(String uname, String pword){
//        new FindUserAsyncTask(userDAO, uname, pword).execute();
//        return loginUser;
//    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private UpdateUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;

        private DeleteAllUsersAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            userDAO.deleteAllUsers();
            return null;
        }
    }


        // returns index out of bounds
//    private static class FindUserAsyncTask extends AsyncTask<User, Void, Void>{
//        private UserDAO userDAO;
//        private String username;
//        private String password;
//
//        private FindUserAsyncTask(UserDAO userDAO, String uname, String pword){
//            this.userDAO = userDAO;
//            this.username = uname;
//            this.password = pword;
//        }
//
//        @Override
//        protected Void doInBackground(User... users) {
//            loginUser = userDAO.findUserByNameAndPass(users[0].getUsername(), users[0].getPassword());
//            return null;
//        }
//    }

//    private class FindUserAsyncTask extends AsyncTask<User, User, User> {
//        private UserDAO userDAO;
//        private User user;
//
//        private FindUserAsyncTask(UserDAO userDAO, User user){
//            this.userDAO = userDAO;
//            this.user = user;
//        }
//
//        @Override
//        protected User doInBackground(User... users) {
//            loginUser = userDAO.findUserByNameAndPass(users[0].getUsername(), users[0].getPassword());
//            return user;
//        }
//    }

//    private class FindUserAsyncTask extends AsyncTask<User, Void, Void> {
//        private UserDAO userDAO;
//
//        private FindUserAsyncTask(UserDAO userDAO){
//            this.userDAO = userDAO;
//        }
//
//        @Override
//        protected Void doInBackground(User... users) {
//            loginUser = userDAO.findUserByNameAndPass(users[0].getUsername(), users[0].getPassword());
//        }
//    }

//    public static class FindUserAsyncTask extends AsyncTask<User, Void, User> {
//        private UserDAO userDAO;
//        private User retrievedUser;
//
//        public interface UserAsyncResponse {
//            void processFinish(User output);
//        }
//
//        public UserAsyncResponse delegate = null;
//
//        public FindUserAsyncTask(UserDAO userDAO, UserAsyncResponse delegate, User user){
//            this.userDAO = userDAO;
//            this.delegate = delegate;
//            this.retrievedUser = user;
//        }
//
//        @Override
//        protected User doInBackground(User... users) {
//            retrievedUser = userDAO.findUserByNameAndPass(users[0].getUsername(), users[0].getPassword());
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(User result) {
//            delegate.processFinish(result);
//        }
//    }
}
