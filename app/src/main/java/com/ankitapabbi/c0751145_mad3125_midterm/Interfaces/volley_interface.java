package com.ankitapabbi.c0751145_mad3125_midterm.Interfaces;

import com.android.volley.VolleyError;

public interface volley_interface {
    void Volley_Success(String Response);
    void Volley_Error(VolleyError error);
    void Volley_Error(String error);
}
