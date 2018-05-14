package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {
    CurrentParty currentParty;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.7F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton3);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Pop.class));
                ib.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });

    }

    /** Called when the user taps the ranking button */
    public void highscoreButton(View view) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        view.startAnimation(buttonClick);
        startActivity(intent);
    }

    /** Called when the user taps the karta button */
    public void kartaButton(View view) {
        Intent intent = new Intent(this, MyLocationDemoActivity.class);
        view.startAnimation(buttonClick);
        startActivity(intent);
    }

    /** Called when the user taps the Create party button */
    public void createparty(View view) {
        Intent intent = new Intent(this, CreateParty.class);
        view.startAnimation(buttonClick);
        startActivity(intent);
    }

   /** Called when the user taps the find party button */
    public void findparty(View view) {
        Intent intent = new Intent(this, FindParty.class);
        view.startAnimation(buttonClick);
        startActivity(intent);
    }


}
