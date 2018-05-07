package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateParty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);
    }

    /** Called when the user taps the Accelerometer button */
    public void skapafestbutton(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
