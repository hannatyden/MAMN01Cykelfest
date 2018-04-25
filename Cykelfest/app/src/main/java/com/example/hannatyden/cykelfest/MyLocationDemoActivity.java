package com.example.hannatyden.cykelfest;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MyLocationDemoActivity extends FragmentActivity
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback, GestureDetector.OnGestureListener{

    private GoogleMap mMap;
    GestureDetector gestureScanner;
    ValueAnimator animateToBigger;
    ValueAnimator animateToSmaller;
    final float startSize= 100;
    final float endSize = 800;
    long animationDuration = 600;
    boolean infoViewOpened = false;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_location_demo);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        gestureScanner = new GestureDetector(this);

        //ViewGroup.MarginLayoutParams parms = (ViewGroup.MarginLayoutParams) mapFragment.getView().getLayoutParams();
        //parms.bottomMargin = 0;
        //parms.topMargin = 100;

        final TextView tv = findViewById(R.id.info);
        tv.bringToFront();



        //Animation that makes the info box bigger
        animateToBigger = ValueAnimator.ofFloat(startSize, endSize);
        animateToBigger.setDuration(animationDuration);
        animateToBigger.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();

                tv.setHeight((int) animatedValue);


            }
        });

        //Animation that makes the info box smaller
        animateToSmaller = ValueAnimator.ofFloat(endSize, startSize);
        animateToSmaller.setDuration(animationDuration);
        animateToSmaller.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();

                tv.setHeight((int) animatedValue);


            }
        });




        //animateToBigger.start();


        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return gestureScanner.onTouchEvent(event);
            }
        });


            //ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();

        //params.height = 900;
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
    }

    public void onViewClick(ValueAnimator animator){
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
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
        Log.i("test", "tastststs");
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
