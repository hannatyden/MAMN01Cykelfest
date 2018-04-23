package com.example.hannatyden.cykelfest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class RankingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textView;
    private ImageView image;
    private TextView rank_nbr;
    private int score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //declaring Sensor Manager and sensor type
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //locate views
        textView = (TextView) findViewById(R.id.txt);
        image = (ImageView) findViewById(R.id.img);
        rank_nbr = (TextView) findViewById(R.id.ranknbr);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent event) {
        //DecimalFormat df = new DecimalFormat("0");
        float x = event.values[0];
        float y = event.values[1];
        float g;
        float r;
        int color = Color.rgb(255, 255, 0); // yellow

        //if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) {
                r = (1-(-x)/7);
                if(r < 0){
                    r= 0;
                }
                g = 1;
                //image.setImageResource(R.drawable.verygood);
                getWindow().getDecorView().setBackgroundColor(Color.rgb(r,g,0));
                textView.setText("x: " + x + "\n y: " + y + "\n r: " + r + "\n g: " + g);
            }
            if (x > 0) {
                g = (1-x/7);
                r = 1;
                if(g < 0){
                    g = 0;    //image.setImageResource(R.drawable.bad);
                }
                getWindow().getDecorView().setBackgroundColor(Color.rgb(r,g,0));
                textView.setText("x: " + x + "\n y: " + y + "\n r: " + r + "\n g: " + g);
            }
       // }
        if (x > (-1) && x < (1) && y > (-1) && y < (1)) {
            //image.setImageResource(R.drawable.good);
            getWindow().getDecorView().setBackgroundColor(color);
            r = 1;
            g = 1;
            textView.setText("x: " + x + "\n y: " + y + "\n r: " + r + "\n g: " + g);
        }

        score = (int) x * 5;
        rank_nbr.setText( "" + score);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister Sensor listener
        sensorManager.unregisterListener(this);
    }
}
