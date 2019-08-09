package com.ankitapabbi.c0751145_mad3125_midterm.Interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface volley_interface_json {
    void Volley_Success(JSONObject Response);
    void Volley_Error(VolleyError error);
    void Volley_Error(String error);
}
