package com.example.hannatyden.cykelfest;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private String address;
    private String course; //förätt, varmrätt, efterrätt
    private LatLng pos;
    private String phoneNbr;
    private String hostName;

    public Location(String address, String course, LatLng pos, String phoneNbr, String hostName){
        this.address = address;
        this.course = course;
        this.pos = pos;
        this.phoneNbr = phoneNbr;
        this.hostName = hostName;
    }

    public String getAddress() {
        return address;
    }

    public String getCourse() {
        return course;
    }

    public LatLng getPos() {
        return pos;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public String getHostName() {
        return hostName;
    }
}
