package com.example.hannatyden.cykelfest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class CreateParty extends AppCompatActivity{
    EditText editText, editText2, editText3, editText4, editText6, editText7;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);

        editText = findViewById(R.id.editText);     //tema
        editText2 = findViewById(R.id.editText2);   //festnamn
        editText3 = findViewById(R.id.editText3);   //datum för fest
        editText4 = findViewById(R.id.editText4);   //plats
        editText6 = findViewById(R.id.editText6);   //max antal pers
        editText7 = findViewById(R.id.editText7);   //sista anmälningsdatum

        vibe = (Vibrator) CreateParty.this.getSystemService(Context.VIBRATOR_SERVICE);

    }


    public void skapafestbutton(View view) {
        if(editText.getText().toString().equals("Tema") || editText.getText().toString().equals("")
                || editText2.getText().toString().equals("Festnamn") || editText2.getText().toString().equals("")
                || editText3.getText().toString().equals("Datum yymmdd") || editText3.getText().toString().equals("")
                || editText4.getText().toString().equals("Plats") || editText4.getText().toString().equals("")
                || editText6.getText().toString().equals("Max antal personer") || editText6.getText().toString().equals("")
                || editText7.getText().toString().equals("Sista anmälningsdatum yymmdd") || editText7.getText().toString().equals("")){
            //skriva ut ett meddelande om att fälten inte är ifyllda
            startActivity(new Intent(this, Pop3.class));

        } else {
            Intent intent = new Intent();
            vibe.vibrate(80);
            ArrayList<String> party = new ArrayList<String>();
            party.add(editText.getText().toString());
            party.add(editText2.getText().toString());
            party.add(editText3.getText().toString());
            party.add(editText4.getText().toString());
            party.add(editText6.getText().toString());
            party.add(editText7.getText().toString());

            intent.putStringArrayListExtra("partyArray", party);
            setResult(RESULT_OK, intent);
            finish();
           // System.out.println(party);

        }

    }

}
