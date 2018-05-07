package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    CurrentParty currentParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


    }

    /** Called when the user taps the Compass button */
    public void rankingButton(View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Accelerometer button */
    public void createparty(View view) {
        Intent intent = new Intent(this, CreateParty.class);
        startActivity(intent);
    }


}
