package com.example.koffie_app;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import android.os.Handler;

public class ConnectionPeriodicTask implements Runnable{
// should be singleton?

    private static ConnectionPeriodicTask instance;
    private Context context;
    private Handler handler;
    private int interval;
    private AppRoomDatabase database;
    private boolean inetConnection;

    private ConnectionPeriodicTask(Context ctx, Handler hndlr, int intrvl, AppRoomDatabase db){
        context = ctx;
        handler = hndlr;
        interval = intrvl;
        database = db;
    }

    public static synchronized ConnectionPeriodicTask getInstance(Context ctx, Handler hndlr,
                                                                  int intrvl, AppRoomDatabase db){
        if (instance == null){
            instance = new ConnectionPeriodicTask(ctx, hndlr, intrvl, db);
        }
        return instance;
    }

    @Override
    public void run() {
        //check connection
        //start request task
        //save to room
//        Log.d(String.valueOf(isOnline()), "run: interval");
        inetConnection = isOnline();
        getData(database);
        handler.postDelayed(this, interval);
    }

    public boolean getInetConnection(){
        return inetConnection;
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)        { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    public void getData(AppRoomDatabase db){
        if (inetConnection){
            new Thread(new GetCoffeeTask(db)).start();
        }
    }
}
