package com.ankitapabbi.c0751145_mad3125_midterm.Interfaces;

public interface AdapterListener {

    public void clicked( String flight_number,String mission_name,String launch_year,
                         String launch_date_unix,String launch_date_utc,String rocket_id,
                         String rocket_name,String rocket_type,String article_link,String wikipedia,
                         String mission_patch);
}
