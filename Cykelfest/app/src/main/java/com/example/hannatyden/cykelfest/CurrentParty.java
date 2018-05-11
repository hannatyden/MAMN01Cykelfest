package com.example.hannatyden.cykelfest;

import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sanimesic on 2018-04-26.
 */

public class CurrentParty implements Serializable {
    private Integer score;
    private String hostName;
    private String phoneNbr;
    private String adress;

    private ArrayList<Location> locations;

    public CurrentParty(){
        Location loc1 = new Location("Sölvegatan 26, 22362, Lund", "Förrätt", "0701234567", "Hanna");
        locations = new ArrayList<Location>();
        locations.add(loc1);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
