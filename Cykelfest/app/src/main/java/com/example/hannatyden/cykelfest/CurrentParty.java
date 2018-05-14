package com.example.hannatyden.cykelfest;

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

    private ArrayList<CurrentLocation> locations;

    public CurrentParty(){
        CurrentLocation loc1 = new CurrentLocation("Sölvegatan 26, 22362, Lund", "Förrätt", "0701234567", "Hanna");
        locations = new ArrayList<CurrentLocation>();
        locations.add(loc1);
    }

    public ArrayList<CurrentLocation> getLocations() {
        return locations;
    }
}
