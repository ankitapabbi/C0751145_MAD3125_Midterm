package com.ankitapabbi.c0751145_mad3125_midterm.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MissionData implements Parcelable {


    private String flight_number;
    private String mission_name;
    private String launch_year;
    private String launch_date_unix;
    private String launch_date_utc;
    private String rocket_id;
    private String rocket_name;
    private String rocket_type;
    private String article_link;
    private String wikipedia;
    private String mission_patch;

    public MissionData() {
    }

    protected MissionData(Parcel in) {
        flight_number = in.readString();
        mission_name = in.readString();
        launch_year = in.readString();
        launch_date_unix = in.readString();
        launch_date_utc = in.readString();
        rocket_id = in.readString();
        rocket_name = in.readString();
        rocket_type = in.readString();
        article_link = in.readString();
        wikipedia = in.readString();
        mission_patch = in.readString();
    }

    public static final Creator<MissionData> CREATOR = new Creator<MissionData>() {
        @Override
        public MissionData createFromParcel(Parcel in) {
            return new MissionData(in);
        }

        @Override
        public MissionData[] newArray(int size) {
            return new MissionData[size];
        }
    };

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public String getLaunch_date_unix() {
        return launch_date_unix;
    }

    public void setLaunch_date_unix(String launch_date_unix) {
        this.launch_date_unix = launch_date_unix;
    }

    public String getLaunch_date_utc() {
        return launch_date_utc;
    }

    public void setLaunch_date_utc(String launch_date_utc) {
        this.launch_date_utc = launch_date_utc;
    }

    public String getRocket_id() {
        return rocket_id;
    }

    public void setRocket_id(String rocket_id) {
        this.rocket_id = rocket_id;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public void setRocket_name(String rocket_name) {
        this.rocket_name = rocket_name;
    }

    public String getRocket_type() {
        return rocket_type;
    }

    public void setRocket_type(String rocket_type) {
        this.rocket_type = rocket_type;
    }

    public String getArticle_link() {
        return article_link;
    }

    public void setArticle_link(String article_link) {
        this.article_link = article_link;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getMission_patch() {
        return mission_patch;
    }

    public void setMission_patch(String mission_patch) {
        this.mission_patch = mission_patch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flight_number);
        dest.writeString(mission_name);
        dest.writeString(launch_year);
        dest.writeString(launch_date_unix);
        dest.writeString(launch_date_utc);
        dest.writeString(rocket_id);
        dest.writeString(rocket_name);
        dest.writeString(rocket_type);
        dest.writeString(article_link);
        dest.writeString(wikipedia);
        dest.writeString(mission_patch);
    }
}
