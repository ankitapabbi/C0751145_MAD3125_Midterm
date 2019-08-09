package com.ankitapabbi.c0751145_mad3125_midterm.Singleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Volley_Singleton {
    private static Volley_Singleton volleySingleton;
    private RequestQueue requestQueue;
    private Context mctx;
    private Volley_Singleton(Context context){
        this.mctx=context;
        this.requestQueue=getRequestQueue();

    }

    private RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized Volley_Singleton getInstance(Context context){
        if (volleySingleton==null){
            volleySingleton=new Volley_Singleton(context);
        }
        return volleySingleton;
    }
    public<T> void addToRequestQue(Request<T> request) {
        requestQueue.add(request);
    }

}


