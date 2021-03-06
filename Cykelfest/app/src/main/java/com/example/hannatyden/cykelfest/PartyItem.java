package com.example.hannatyden.cykelfest;

import android.os.Parcel;
import android.os.Parcelable;

public class PartyItem implements Parcelable {
    public String theme;
    public String name;
    public String date; //datum för fest
    public String address;
    public String max;
    public String endDate; //senaste anmälningsdatum
    public String attending;


    public PartyItem(String theme, String name, String date, String address, String max, String endDate, String attending){
        this.theme = theme;
        this.name = name;
        this.date = date;
        this.address = address;
        this.max = max;
        this.endDate = endDate;
        this.attending = attending;

    }

    public String getTheme() {
        return theme;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getMax() {
        return max;
    }

    public String getEndDate() {
        return endDate;
    }

    public String isAttending(){
        return attending;
    }

    //write object values to parcel for storage
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //write all properties to the parcel
        dest.writeString(theme);
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(address);
        dest.writeString(max);
        dest.writeString(endDate);
    }

    //constructor used for parcel
    public PartyItem(Parcel parcel){
        //read and set saved values from parcel
        theme = parcel.readString();
        name = parcel.readString();
        date = parcel.readString();
        address = parcel.readString();
        max = parcel.readString();
        endDate = parcel.readString();
    }

    public void setTheme(String s){
        this.theme = s;
    }

    public void setName(String s){
        this.theme = s;
    }

    public void setAddress(String s){
        this.theme = s;
    }

    public void setDate(String s){
        this.theme = s;
    }

    public void setMax(String s){
        this.theme = s;
    }

    public void setEndDate(String s){
        this.theme = s;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<PartyItem> CREATOR = new Parcelable.Creator<PartyItem>(){

        @Override
        public PartyItem createFromParcel(Parcel parcel) {
            return new PartyItem(parcel);
        }

        @Override
        public PartyItem[] newArray(int size) {
            return new PartyItem[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
