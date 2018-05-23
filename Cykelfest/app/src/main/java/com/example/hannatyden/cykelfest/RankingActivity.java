package com.example.hannatyden.cykelfest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView textView;
    private ImageView image;
    private TextView rank_nbr;
    private Button okButton;
    private Display display;
    private ImageView cursor;
    private boolean secondDestination = false;
    private ConstraintLayout layout;
    private int scoreX;
    private int scoreY;
    private int width;
    private int height;
    private Button backButton;
    Vibrator vibe;


    private float vals[] = new float[2];
    private float alpha = 0.2f; //Hur snabbt x ändras

    private int counter = 0; //testvariabel
    private boolean isClicked = false; //Används för att pausa rankingen

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //declaring Sensor Manager and accelerometer type
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        //locate views
        cursor = (ImageView) findViewById(R.id.Cursor);
        textView = (TextView) findViewById(R.id.txt);
        image = (ImageView) findViewById(R.id.img);
        rank_nbr = (TextView) findViewById(R.id.ranknbr);
        layout = (ConstraintLayout) findViewById(R.id.RankingActivityLayout);
        okButton = (Button) findViewById(R.id.okButton);
        backButton = (Button) findViewById(R.id.backButton);
        okButton.setVisibility(View.INVISIBLE);
        backButton.setVisibility(View.INVISIBLE);
        display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y - 400;


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Anropas när man clickar någonstans på skärmen under aktiviteten.
             //   counter++;
              //  textView.setText("I have been clicked: " + counter + " times");

                if(!isClicked) {
                    Log.i("1" , "I have been pressed");
                    okButton.setVisibility(View.VISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                    isClicked = true;
                }
            }
        });

    }

    public void backButtonPressed(View view){

        okButton.setVisibility(View.INVISIBLE);
        backButton.setVisibility(View.INVISIBLE);
        isClicked = false;
        vibe.vibrate(50);

    }

    public void okButtonPressed(View view) {
            isClicked = true;
            Intent intent = new Intent();
            intent.putExtra("editTextValue", "flag");
            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            setResult(RESULT_OK, intent);

            Log.i("2", "Is finishing");
        vibe.vibrate(50);
            finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isClicked) { //Ingen data hanteras när man tryckt på skärmen (VARNING: Busy Wait är inte bra för batteritid osv)


        } else {
            vals[0] = vals[0] + alpha * (event.values[0] - vals[0]);
            vals[1] = vals[1] + alpha * (event.values[1] - vals[1]);

            float x = vals[0];
            float y = vals[1];
            float g;
            float r;

            float xTrans = width/17;
            float yTrans = height/17;

            float centerX = width/2;
            float centerY = height/2 + height/9;

            float xpos = (x+1) * xTrans;
            float ypos = (y-1) * yTrans;

            if (xpos > 360){
                cursor.setX(180);
            } else if (xpos <  -260){
                cursor.setX(800);
            } else {
                cursor.setX(centerX - xpos);
            }

            if (ypos < 30){
                cursor.setY(900);
            } else if (ypos > 665){
                cursor.setY(1510);
            } else {
                cursor.setY(centerY + ypos);
            }


            r = 1;
            g = 1;

            if(x > -y) {
                g = 1 - (x+y)/14;
                r = 1;
                if(g < 0) {
                    g = 0;
                }
                getWindow().getDecorView().setBackgroundColor(Color.rgb(r,g,0));

            }  else if (x < -y) {
                r = 1 + (x+y)/14;
                g = 1;
                if(r < 0) {
                    r = 0;
                }
                getWindow().getDecorView().setBackgroundColor(Color.rgb(r,g,0));
            } else if (x > (-1) && x < (1) && y > (-1) && y < (1)) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                r = 1;
                g = 1;
            }

            float tempX = (cursor.getX() - 150)/(62*2);
            float tempY = (1500 - cursor.getY())/(65*2);
            if(cursor.getY() == 900) {
                tempY = 5f;
            }
            float totalscoreF = tempX + tempY;
            scoreX = (int) tempX;
            scoreY = (int) tempY;
            int totalscore = (int) totalscoreF;
            rank_nbr.setText("" + (totalscore));


            //draw image
            String debugString = ("Score X : " + scoreX + "\n Score Y : " + scoreY + "\n xpos: " + xpos + "\n ypos: " + ypos + "\nx: " + cursor.getX() + "\nY: " + cursor.getY());
            textView.setText("");

        }



    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME); //Ändrade till SENSOR_DELAY_GAME för att få det smooth, kan ställa in i microsekunder på högre APIer
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister Sensor listener
        sensorManager.unregisterListener(this);
    }
}
