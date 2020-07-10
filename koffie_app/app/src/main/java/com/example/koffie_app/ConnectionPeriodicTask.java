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

    // TODO: 09/07/2020 CHANGE URL ROUTES TO CORRECT COFFEE TABLES 
    private final String URL_MAUR = "http://192.168.178.115:8000/api/coffee";
    private final String URL_AMOS = "http://192.168.2.6:8000/api/coffee";
    private final String URL_HERO = "https://still-atoll-19210.herokuapp.com/api/coffee";

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

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL_HERO, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
//                            Log.d("onResponse: ", String.valueOf(response.getJSONObject(0).get
//                            ("recipe_name")));
                            for (int i = 0; i < response.length(); i++){
                                int id = i;
                                String name = (String) response.getJSONObject(i).get("coffee_name");
                                String ingredients = (String) response.getJSONObject(i).get("coffee_ingredients");
                                String description = (String) response.getJSONObject(i).get("coffee_description");
                                String bean = (String) response.getJSONObject(i).get("coffee_bean");
                                int servings = (int) response.getJSONObject(i).get("coffee_servings");
                                int preptime = (int) response.getJSONObject(i).get("coffee_preptime");
                                String image = (String) response.getJSONObject(i).get("coffee_image");

                                Coffee coffeeObj = new Coffee(id, name, ingredients, description, bean, servings, preptime, image);
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
