package com.ankitapabbi.c0751145_mad3125_midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailScreen extends AppCompatActivity {

    ImageView mPatch;
    TextView missionName,launchYear,launchDateUnix,launchDateUtc,rocketId,rocketName,rocketType,articallink;
    String wikiLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        

        Intent intent = getIntent();

        intent.getStringExtra("flight_number");
        intent.getStringExtra("mission_name");
        intent.getStringExtra("launch_year");
        intent.getStringExtra("launch_date_unix");
        intent.getStringExtra("launch_date_utc");
        intent.getStringExtra("rocket_id");
        intent.getStringExtra("rocket_name");
        intent.getStringExtra("rocket_type");
        intent.getStringExtra("article_link");
        intent.getStringExtra("wikipedia");
        intent.getStringExtra("mission_patch");


    }
}
