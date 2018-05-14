package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PartyInfoPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_info_pop_up);

        Intent intent = getIntent();
        String val1 = intent.getStringExtra("1");
        String val2 = intent.getStringExtra("2");
        String val3 = intent.getStringExtra("3");
        String val4 = intent.getStringExtra("4");
        String val5 = intent.getStringExtra("5");
        String val6 = intent.getStringExtra("6");

        TextView tv = (TextView) findViewById(R.id.textview);
        tv.setText("Namn: " + val1 + "\n" + "Tema: " + val2 + "\n" + "Datum för fest: " + val3 + "\n" + "Adress: " + val4 + "\n" + "Max antal personer: " + val5 + "\n" + "Datum för sista anmälan: " + val6
        );
    }

}
