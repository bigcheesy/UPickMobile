package com.example.dcyberpanda.upickmobile;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by D'CyberPanda on 6/22/2017.
 */


public class ConnectionSingleton {
    private static ConnectionSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private ConnectionSingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ConnectionSingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new ConnectionSingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
