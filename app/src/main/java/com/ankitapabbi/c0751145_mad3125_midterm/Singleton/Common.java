package com.ankitapabbi.c0751145_mad3125_midterm.Singleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.volley_interface;
import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.volley_interface_json;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Common {
    public static void volley_callback(Context context, String url, final HashMap<String,String> params, final volley_interface callback)
    {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty() || response.trim().equals(""))
                {
                    callback.Volley_Error("Error Connecting To Server");
                }
                else
                {
                    callback.Volley_Success(response);
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null)
                {
                    callback.Volley_Error(error.getMessage());
                }
                else
                {
                    if(error == null){
                        callback.Volley_Error("Something Went Wrong");
                    }else{
                        callback.Volley_Error(error.toString());
                    }

                }
            }
        }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
        Volley_Singleton.getInstance(context.getApplicationContext()).addToRequestQue(request);
    }

    public static void JsonVolleyCallBack(Context context, String url, final JSONObject jObject, final volley_interface_json callback) {
        JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, url, jObject,
                new Response.Listener < JSONObject > () {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callback.Volley_Success(jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            callback.Volley_Error(error.getMessage());
                        } else {
                            if (error == null) {
                                callback.Volley_Error("Something Went Wrong");
                            } else {
                                callback.Volley_Error(error.toString());
                            }
                        }
                    }
                });
        Volley_Singleton.getInstance(context.getApplicationContext()).addToRequestQue(jobReq);
    }
}

