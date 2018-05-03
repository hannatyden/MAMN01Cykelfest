package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DestInfoScreen extends AppCompatActivity {
    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest_info_screen);

        infoView = (TextView) findViewById(R.id.infoView);
        infoView.setText("Hej");
    }

    public void rankingButton(View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }
}
