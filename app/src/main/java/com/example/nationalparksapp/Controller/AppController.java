package com.example.nationalparksapp.Controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//This is Singleton
public class AppController extends Application {

    private static AppController instance;
    private RequestQueue requestQueue;

    public static synchronized AppController getInstance(){
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
