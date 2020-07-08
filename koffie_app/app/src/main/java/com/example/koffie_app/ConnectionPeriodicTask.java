package com.example.koffie_app;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionPeriodicTask implements Runnable{
// should be singleton?

    private static ConnectionPeriodicTask instance;
    private Context context;
    private Handler handler;
    private int interval;
    private CoffeeViewModel coffeeViewModel;
    private boolean inetConnection;

    private final String URL = "http://192.168.56.1:8000/api/recipes";

    private ConnectionPeriodicTask(Context ctx, Handler hndlr, int intrvl, CoffeeViewModel coffeeVM){
        context = ctx;
        handler = hndlr;
        interval = intrvl;
        coffeeViewModel = coffeeVM;
    }

    public static synchronized ConnectionPeriodicTask getInstance(Context ctx, Handler hndlr,
                                                                  int intrvl, CoffeeViewModel coffeeVM){
        if (instance == null){
            instance = new ConnectionPeriodicTask(ctx, hndlr, intrvl, coffeeVM);
        }
        return instance;
    }

    @Override
    public void run() {
        //check connection
        //start request task
        //save to room
        Log.d(String.valueOf(isOnline()), "run: interval");
        inetConnection = isOnline();
//        getData(database);

        RequestQueue queue =
                VolleySingleton.getInstance(context).getRequestQueue();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
//                new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
////                try {
////                    jobs[5] = new Job(response.get("name").toString(), "lightsaber", 6);
////                    recyclerViewAdapter.notifyDataSetChanged();
////                    Log.d("api response", response.getJSONObject("recipe_id").toString());
////                    Coffee coffee = new Coffee(response.get("0").toString(), "no", "#fff", 1);
////                }finally {
////
////                }
//                Log.d("api response", String.valueOf(response));
//                Log.d("is it working?", "onResponse: ");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(error.getMessage(), "onErrorResponse: ");
//                Log.e("get error", "onErrorResponse: ", error);
//            }
//        });

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.2" +
//                ".6:8000/api/recipes", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("onResponse: ", response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(error.getMessage(), "onErrorResponse: ");
//            }
//        });

//        List<Coffee> coffeeArray = new ArrayList<Coffee>();

        final Coffee[] coffee = new Coffee[20];// TODO: 07/07/2020 make arraylist

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
//                            Log.d("onResponse: ", String.valueOf(response.getJSONObject(0).get
//                            ("recipe_name")));
                            for (int i = 0; i < response.length(); i++){
//                                String id = (String) response.getJSONObject(i).get("recipe_name");
                                int id = i+1;
                                String name = (String) response.getJSONObject(i).get("recipe_name");
                                String description = (String) response.getJSONObject(i).get("recipe_ingredients");
                                String beans = (String) response.getJSONObject(i).get("coffee_bean");
                                int volume = (int) response.getJSONObject(i).get("coffee_servings");
                                String roast = "black";
                                int time = (int) response.getJSONObject(i).get("coffee_prep_time");

                                Coffee coffeeObj = new Coffee(id, name, description, beans, volume, roast, time);
                                coffeeViewModel.insert(coffeeObj);
//                                coffee[i] = coffeeObj;
//                                coffeeArray[i] = appendValue(coffeeArray[0], coffeeObj);
//                                coffeeArray.add(coffeeObj);
//                                Log.d("onResponse array: ", String.valueOf(coffeeArray.get(i).getName()));
                            }

//                            Log.d(String.valueOf(coffee[1].getId()), "star tthread: ");
//                            new Thread(new InsertCoffeeTask(database, coffee)).start();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(error.getMessage(), "onErrorResponse: ");
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


//        Log.d(String.valueOf(coffeeArray.get(0).getName()), "star tthread: ");
//        new Thread(new InsertCoffeeTask(database, coffeeArray)).start();

        //store

        handler.postDelayed(this, 5000);
    }

//    private Coffee[] appendValue(Coffee[] cffe, Coffee newObj) {
//        ArrayList<Coffee> temp = new ArrayList<Coffee>(Arrays.asList(cffe));
//        temp.add(newObj);
//        return (Coffee[]) temp.toArray();
//    }

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

//    public void getData(AppRoomDatabase db){
//        if (inetConnection){
//            new Thread(new GetCoffeeTask(db)).start();
//        }
//    }
}
