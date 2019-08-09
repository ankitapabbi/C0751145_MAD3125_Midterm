package com.ankitapabbi.c0751145_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ankitapabbi.c0751145_mad3125_midterm.Adapter.MyAdapter;
import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.volley_interface;
import com.ankitapabbi.c0751145_mad3125_midterm.Model.MissionData;
import com.ankitapabbi.c0751145_mad3125_midterm.Singleton.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    RecyclerView dataRecyler;
    RecyclerView.Adapter adapter;
    List<MissionData> listData = new ArrayList<>();
    private RequestQueue requestQueue;
    private final String JSON_URL="https://api.spacexdata.com/v3/launches";
    private JsonArrayRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        dataRecyler = (RecyclerView)findViewById(R.id.dataRecyler);
//        queue = Volley.newRequestQueue(this);

        dataRecyler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dataRecyler.setNestedScrollingEnabled(false);
        listData.clear();
        adapter= new MyAdapter(listData,getApplicationContext());
        dataRecyler.setAdapter(adapter);
       // volley_missionData();
        jsonRequest();

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

                        JSONArray array1 = jsonObject.getJSONArray("rocket");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
}
