package com.ankitapabbi.c0751145_mad3125_midterm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

public class DetailScreen extends AppCompatActivity {

    ImageView mPatch;
    TextView missionName,launchYear,launchDateUnix,launchDateUtc,rocketId,rocketName,rocketType,articallink,flightNumber;
    String wikiLink;
    FloatingActionButton btninfo;
    Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        mPatch = (ImageView)findViewById(R.id.mPatch);
        missionName = (TextView)findViewById(R.id.mission);
        launchYear = (TextView)findViewById(R.id.missionYear);
        launchDateUnix = (TextView)findViewById(R.id.launchDate1);
        launchDateUtc = (TextView)findViewById(R.id.launchDate2);
        rocketId = (TextView)findViewById(R.id.rocketId);
        rocketName = (TextView)findViewById(R.id.rocketName);
        rocketType = (TextView)findViewById(R.id.rocketType);
        articallink = (TextView)findViewById(R.id.arcticalLink);
        flightNumber = (TextView)findViewById(R.id.flightNumber);
        btninfo = (FloatingActionButton) findViewById(R.id.btninfo);
        toolbar = (Toolbar)findViewById(R.id.toolbar);





        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  tb.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        final Intent intent = getIntent();

        flightNumber.setText(intent.getStringExtra("flight_number"));
        missionName.setText(intent.getStringExtra("mission_name"));
        launchYear.setText(intent.getStringExtra("launch_year"));
        launchDateUnix.setText(intent.getStringExtra("launch_date_unix"));
        launchDateUtc.setText(intent.getStringExtra("launch_date_utc"));
        rocketId.setText(intent.getStringExtra("rocket_id"));
        rocketName.setText(intent.getStringExtra("rocket_name"));
        rocketType.setText(intent.getStringExtra("rocket_type"));
        articallink.setText(intent.getStringExtra("article_link"));
        wikiLink = intent.getStringExtra("wikipedia");
        Glide.with(getApplicationContext()).load(intent.getStringExtra("mission_patch")).into(mPatch);


        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1 = new Intent(getApplicationContext(), WebViewScreen.class);
            intent1.putExtra("url",wikiLink);
            intent1.putExtra("missionname",missionName.getText().toString());
            startActivity(intent1);
            }
        });
    }
}
