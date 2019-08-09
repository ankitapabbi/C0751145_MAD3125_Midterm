package com.ankitapabbi.c0751145_mad3125_midterm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ankitapabbi.c0751145_mad3125_midterm.Adapter.MyAdapter;
import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.AdapterListener;
import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.volley_interface;
import com.ankitapabbi.c0751145_mad3125_midterm.Model.MissionData;
import com.ankitapabbi.c0751145_mad3125_midterm.Singleton.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements AdapterListener {

    RecyclerView dataRecyler;
    RecyclerView.Adapter adapter;
    List<MissionData> listData = new ArrayList<>();
    private RequestQueue requestQueue;
    private final String JSON_URL="https://api.spacexdata.com/v3/launches";
    private JsonArrayRequest request;

    Button logout;
    SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private MissionData md;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        dataRecyler = (RecyclerView)findViewById(R.id.dataRecyler);
        logout = (Button)findViewById(R.id.logoutBtn);
        md = new MissionData();
//        queue = Volley.newRequestQueue(this);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        dataRecyler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dataRecyler.setNestedScrollingEnabled(false);
       // listData.clear();
        jsonRequest();
        adapter= new MyAdapter(listData,getApplicationContext(),this);
        dataRecyler.setAdapter(adapter);
       // volley_missionData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
                editor.remove("userEmail");
                editor.remove("userPassword");
                editor.apply();

                Intent  in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
            }
        });

    }

    protected void volley_missionData() {
        String url = "https://api.spacexdata.com/v3/launches";

//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                      //  Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_LONG).show();
//                        if (response != null) {
//                            try {
//                                JSONObject jsonObj = new JSONObject(response);
//
//
//                            }catch (JSONException e) {
//                                Log.e("JSONPARSEEXCEPTION", e.getMessage());
//                            }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });


        HashMap<String, String> param = new HashMap<String, String>();

        Common.volley_callback(getApplicationContext(),"https://api.spacexdata.com/v3/launches",param, new volley_interface() {


            @Override
            public void Volley_Success(String Response) {
                Log.d("res",Response);
                JSONObject jsonObject=null;
                for( int i = 0 ;i< Response.length();i++) {
//
//                    try {
////                        jsonObject=Response
////                        Anime anime=new Anime();
////                        anime.setName(jsonObject.getString("name"));
////                        anime.setCategorie(jsonObject.getString("categorie"));
////                        anime.setRating(jsonObject.getString("Rating"));
////                        anime.setStudio(jsonObject.getString("studio"));
////                        anime.setImage_url(jsonObject.getString("img"));
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                }

            }

            @Override
            public void Volley_Error(VolleyError error) {

            }

            @Override
            public void Volley_Error(String error) {

            }
        });
    }
    private void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("Check",response+"");
                JSONObject jsonObject=null;
                for( int i = 0 ;i< response.length();i++){

                    try {
                        jsonObject=response.getJSONObject(i);
                        MissionData missionData=new MissionData();
                        missionData.setFlight_number(jsonObject.getString("flight_number"));
                        missionData.setMission_name(jsonObject.getString("mission_name"));
                        missionData.setLaunch_year(jsonObject.getString("launch_year"));
                        missionData.setLaunch_date_unix(jsonObject.getString("launch_date_unix"));
                        missionData.setLaunch_date_utc(jsonObject.getString("launch_date_utc"));

                        JSONObject array1 = jsonObject.getJSONObject("rocket");

                        for (int ii=0;ii<=array1.length();ii++){

                             missionData.setRocket_id(array1.getString("rocket_id"));
                             missionData.setRocket_name(array1.getString("rocket_name"));
                             missionData.setRocket_type(array1.getString("rocket_type"));

                        }
                        JSONObject array2 = jsonObject.getJSONObject("links");

                        for (int io=0;io<=array2.length();io++){

                            missionData.setArticle_link(array2.getString("article_link"));
                            missionData.setWikipedia(array2.getString("wikipedia"));
                            missionData.setMission_patch(array2.getString("mission_patch"));

                        }
                        listData.add(missionData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    adapter.notifyDataSetChanged();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public void clicked(String flight_number, String mission_name, String launch_year, String launch_date_unix, String launch_date_utc, String rocket_id, String rocket_name, String rocket_type, String article_link, String wikipedia, String mission_patch) {

        Intent intent = new Intent(HomeScreen.this,DetailScreen.class);

    

        intent.putExtra("flight_number",flight_number);
        intent.putExtra("mission_name",mission_name);
        intent.putExtra("launch_year",launch_year);
        intent.putExtra("launch_date_unix",launch_date_unix);
        intent.putExtra("launch_date_utc",launch_date_utc);
        intent.putExtra("rocket_id",rocket_id);
        intent.putExtra("rocket_name",rocket_name);
        intent.putExtra("rocket_type",rocket_type);
        intent.putExtra("article_link",article_link);
        intent.putExtra("wikipedia",wikipedia);
        intent.putExtra("mission_patch",mission_patch);

        Toast.makeText(getApplicationContext(),mission_name,Toast.LENGTH_LONG).show();

        startActivity(intent);

    }
}
