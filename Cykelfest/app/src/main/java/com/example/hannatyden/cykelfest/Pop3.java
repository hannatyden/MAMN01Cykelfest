package com.example.hannatyden.cykelfest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class Pop3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow3);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        if(layout == null) {
            System.out.println(layout);
        }
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Anropas när man clickar någonstans på skärmen under aktiviteten.
                //   counter++;
                //  textView.setText("I have been clicked: " + counter + " times");
                Log.i("1" , "I have been pressed");

                finish();
            }

        });

    }
}
