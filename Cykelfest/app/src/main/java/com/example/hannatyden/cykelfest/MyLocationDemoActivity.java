package com.example.hannatyden.cykelfest;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MyLocationDemoActivity extends FragmentActivity
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback, GestureDetector.OnGestureListener{


    /* Klassen för kartskärmen, innehåller en GestureDetector som läser av rörelser som görs på Info-boxen längst
     * upp på skärmen, och förstorar/förminskar den då rörelser läses av. */
    private GoogleMap mMap;
    private Context context = this;
    GestureDetector gestureScanner;
    ValueAnimator animateToBigger;
    ValueAnimator animateToSmaller;
    private CurrentLocation currentLoc;
    private String strEditText;

    private Marker currentMarker;
    private boolean secondDestination = false;
    /* Attribut som används till animation, startSize/endSize anger vilken storlek som boxen ska förstoras/förminskas till
    *  och animationDuration anger hur snabbt animationen ska utföras i milisekunder. */
    final float startSize= 150;
    final float endSize = 500;
    long animationDuration = 600;
    private TextToSpeech tts;

    // Boolean som används för att kolla ifall info-boxen är förstorad eller förminskad
    boolean infoViewOpened = false;

    private LatLng currentPartyLoc;
    private TextView tv;

    private boolean flag;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_location_demo);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        gestureScanner = new GestureDetector(this);

        tv = findViewById(R.id.info);
        tv.bringToFront();
        tv.setHeight(150);

        tts = new TextToSpeech(MyLocationDemoActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        //mMap.setMyLocationEnabled(true);

/*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            // Show rationale and request permission.
        }*/







        //Animation som gör info-boxen större ifall en rörelse görs på boxen
        animateToBigger = ValueAnimator.ofFloat(startSize, endSize);
        animateToBigger.setDuration(animationDuration);
        animateToBigger.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();

                tv.setHeight((int) animatedValue);


            }
        });

        //Animation som gör info-boxen mindre ifall en rörelse görs på boxen
        animateToSmaller = ValueAnimator.ofFloat(endSize, startSize);
        animateToSmaller.setDuration(animationDuration);
        animateToSmaller.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv.setHeight((int) animatedValue);
            }
        });

        // Listener som läggs till info TextView:n och läser av rörelser
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return gestureScanner.onTouchEvent(event);
            }
        });

        mapFragment.getMapAsync(this);

        //fult ta bort vid tillfälle
        flag = true;
        currentLoc = new CurrentLocation("IKDC", "Förrätt", "0723153789", "Sven Svensson");
        //locations.add(new CurrentLocation("IKDC", "Förrätt", "0723153789", "Sven Svensson"));
        //locations.add(new CurrentLocation("Korsningen", "Huvudrätt", "0723153631", "Bengt Bengtsson"));

    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(55.713782, 13.211657) , 15.7f));
        // TODO: Before enabling the My CurrentLocation layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            // Show rationale and request permission.
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false); // delete default button

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        ImageButton imgbtn = (ImageButton) findViewById(R.id.bikeButton); //your button
        final Intent i = new Intent(this, RankingActivity.class);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
//                        LatLng(location.getLatitude(),
//                        location.getLongitude()), 17));



                //startActivity(i);
                startActivityForResult(i, 1);
            }
        });




       // mMap.getUiSettings().setMyLocationButtonEnabled(false);

        currentPartyLoc = new LatLng(55.714799, 13.212359); //IKDC



        currentMarker = mMap.addMarker(new MarkerOptions()
                .position(currentPartyLoc)
                .title("Current party location "));


//
//
//        LatLng origin = (new LatLng(location.getLatitude(), location.getLongitude()));
//        LatLng dest = currentPartyLoc;

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Double curLat = location.getLatitude();
                Double curLong = location.getLongitude();
//                if(flag) {
//                     CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 18);
//                     mMap.animateCamera(cu);
//                     flag = !flag;
//                }
                Double distance = Math.hypot(curLat - currentPartyLoc.latitude, curLong - currentPartyLoc.longitude);
                Log.i("test", distance.toString());
                if(distance < 50E-5){

                    tts.setLanguage(Locale.US);
                    tts.speak("You have arrived to the party", TextToSpeech.QUEUE_ADD, null);

                    Log.i("test", "You are at your party" + distance.toString());
                    tv.setText("Du har kommit till festens destination!");
                    // Get instance of Vibrator from current Context
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    // Vibrate for 400 milliseconds
                    long[] pattern = { 400, 400, 400};
                    v.vibrate(pattern , -1);
                    //v.vibrate(400);
//                    Intent intent = new Intent(context, DestInfoScreen.class);
//                    startActivity(intent);

                    startActivityForResult(i, 1);

                } else {
                    tv.setText("Du är inte på festens destination! \n \n" +  "Adress: " + currentLoc.getAddress() + "\n"
                     + "Rätt: " + currentLoc.getCourse() + "\n" + "Telefonnummer: " + currentLoc.getPhoneNbr() + "\n" +
                    "Värd: " + currentLoc.getHostName());


                }
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                strEditText = data.getStringExtra("editTextValue");
                if (strEditText.equals("flag")) {
                    if (!secondDestination) {
                        currentMarker.remove();
                        secondDestination = true;
                        currentPartyLoc = new LatLng(55.713528, 13.211162);
                        currentLoc = new CurrentLocation("Korsningen", "Huvudrätt", "0723153631", "Bengt Bengtsson");
                        tv.setText("Du är inte på festens destination! \n \n" + "Adress: " + currentLoc.getAddress() + "\n"
                                + "Rätt: " + currentLoc.getCourse() + "\n" + "Telefonnummer: " + currentLoc.getPhoneNbr() + "\n" +
                                "Värd: " + currentLoc.getHostName());

                        currentMarker = mMap.addMarker(new MarkerOptions()
                                .position(currentPartyLoc)
                                .title("Current party location "));
                        Log.i("tag", "flag");

                        //pop up som säger att man nu ska ta sig till en annan location
                        startActivity(new Intent(MyLocationDemoActivity.this, Pop2.class));

                        ImageButton ib = (ImageButton) findViewById(R.id.bikeButton);
                        ib.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                    } else {
                        currentMarker.remove();

                        currentPartyLoc = new LatLng(55.697738, 13.186190);
                        currentLoc = new CurrentLocation("Stadsparken", "Efterrätt", "0724003631", "Filip Stjernström");
                        tv.setText("Du är inte på festens destination! \n \n" + "Adress: " + currentLoc.getAddress() + "\n"

                                + "Rätt: " + currentLoc.getCourse() + "\n" + "Telefonnummer: " + currentLoc.getPhoneNbr() + "\n" +
                                "Värd: " + currentLoc.getHostName());

                        currentMarker = mMap.addMarker(new MarkerOptions()
                                .position(currentPartyLoc)
                                .title("Current party location "));

                        startActivity(new Intent(MyLocationDemoActivity.this, Pop2.class));
                    }
                }
            }
        }
    }

    public void onViewClick(ValueAnimator animator){
    }




    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    public void arrowButtonPressed(View view) {
        if(infoViewOpened){
            animateToSmaller.start();
            infoViewOpened = false;
        } else {
            animateToBigger.start();
            infoViewOpened = true;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    /** Called when the user taps the Accelerometer button */
    public void infoClick() {

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(infoViewOpened){
            animateToSmaller.start();
            infoViewOpened = false;
        } else {
            animateToBigger.start();
            infoViewOpened = true;
        }

        return true;
    }



}
