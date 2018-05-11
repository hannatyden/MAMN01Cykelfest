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

    /** Called when the user taps the ranking button */
    public void rankingButton(View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the karta button */
    public void kartaButton(View view) {
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }

    /** Called when the user taps the Create party button */
    public void createparty(View view) {
        Intent intent = new Intent(this, CreateParty.class);
        startActivity(intent);
    }

   /** Called when the user taps the find party button */
    public void findparty(View view) {
        Intent intent = new Intent(this, FindParty.class);
        startActivity(intent);
    }


}
