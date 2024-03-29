package com.ankitapabbi.c0751145_mad3125_midterm.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankitapabbi.c0751145_mad3125_midterm.Interfaces.AdapterListener;
import com.ankitapabbi.c0751145_mad3125_midterm.Model.MissionData;
import com.ankitapabbi.c0751145_mad3125_midterm.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<MissionData> listData = new ArrayList<>();
    Context context;
    AdapterListener adapterListener;

    public MyAdapter(List<MissionData> listData, Context context, AdapterListener adapterListener) {
        this.listData = listData;
        this.context = context;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mission_design,viewGroup,
                false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final MissionData data = listData.get(i);
        myViewHolder.missionName.setText(String.valueOf(data.getMission_name()));
        myViewHolder.launchYear.setText(data.getLaunch_year());
        Glide.with(context).load(data.getMission_patch()).into(myViewHolder.missionPatch);
        myViewHolder.myCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            adapterListener.clicked(data.getFlight_number(),data.getMission_name(),
                    data.getLaunch_year(),data.getLaunch_date_unix(),data.getLaunch_date_utc(),
                    data.getRocket_id(),data.getRocket_name(),data.getRocket_type(),
                    data.getArticle_link(),data.getWikipedia(),data.getMission_patch());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView missionName,launchYear;
        ImageView missionPatch;
        CardView myCardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            missionName = (TextView)itemView.findViewById(R.id.missionName);
            launchYear = (TextView)itemView.findViewById(R.id.launchYear);
            missionPatch =(ImageView)itemView.findViewById(R.id.missionPatch);
            myCardview = (CardView)itemView.findViewById(R.id.myCardview);
        }
    }
}
