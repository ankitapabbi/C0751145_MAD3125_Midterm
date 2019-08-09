package com.ankitapabbi.c0751145_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class HomeScreen extends AppCompatActivity {

    RecyclerView dataRecyler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        dataRecyler = (RecyclerView)findViewById(R.id.dataRecyler);

    }
}
