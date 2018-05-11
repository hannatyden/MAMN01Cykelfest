package com.example.hannatyden.cykelfest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class CreateParty extends AppCompatActivity implements View.OnClickListener{
    //FindParty fp = new FindParty();
   EditText editText, editText2, editText3, editText4, editText6, editText7;
    //ArrayList<PartyItem> arrayOfParties = FindParty.getData();
    //String partyitem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);

        editText = (EditText) findViewById(R.id.editText);
        editText.setOnClickListener(this);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setOnClickListener(this);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText3.setOnClickListener(this);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText4.setOnClickListener(this);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText6.setOnClickListener(this);
        editText7 = (EditText) findViewById(R.id.editText7);
        editText7.setOnClickListener(this);

        editText = findViewById(R.id.editText);     //tema
        editText2 = findViewById(R.id.editText2);   //festnamn
        editText3 = findViewById(R.id.editText3);   //datum för fest
        editText4 = findViewById(R.id.editText4);   //plats
        editText6 = findViewById(R.id.editText6);   //max antal pers
        editText7 = findViewById(R.id.editText7);   //sista anmälningsdatum

    }

    /** Called when the user taps the Accelerometer button */
    public void skapafestbutton(View view) {
        if(editText.getText().toString().equals("Tema") || editText.getText().toString().equals("")
                || editText2.getText().toString().equals("Festnamn") || editText2.getText().toString().equals("")
                || editText3.getText().toString().equals("Datum yymmdd") || editText3.getText().toString().equals("")
                || editText4.getText().toString().equals("Plats") || editText4.getText().toString().equals("")
                || editText6.getText().toString().equals("Max antal personer") || editText6.getText().toString().equals("")
                || editText7.getText().toString().equals("Sista anmälningsdatum yymmdd") || editText7.getText().toString().equals("")){
            //skriva ut ett meddelande om att fälten inte är ifyllda
        } else {
           // PartyItem pi = new PartyItem("1","2","3","4", "5" , "6");
            Intent intent = new Intent(CreateParty.this, FindParty.class);
            PartyItem partyItem = intent.getParcelableExtra("Party");
            intent.putExtra("Party", partyItem);
            startActivity(intent);
//            Intent intent = new Intent();
//            ArrayList<String> partyinfo = new ArrayList<>();
//            partyinfo.add(editText.getText().toString());
//            partyinfo.add(editText2.getText().toString());
//            partyinfo.add(editText3.getText().toString());
//            partyinfo.add(editText4.getText().toString());
//            partyinfo.add(editText6.getText().toString());
//            partyinfo.add(editText7.getText().toString());
//            intent.putStringArrayListExtra("PartyArray", partyinfo);
//            setResult(RESULT_OK, intent);
//            finish();
//            System.out.println("SENT PARTY INFO");
        }


    }

//    private void passArrayList() {
//        final ArrayList<String> arrayOfParties = new ArrayList<>();
//       // arrayOfParties.add(new PartyItem(editText.getText().toString(), editText2.getText().toString(), editText4.getText().toString(), editText6.getText().toString()));
//        arrayOfParties.add(editText.getText().toString());
//        arrayOfParties.add(editText2.getText().toString());
//        arrayOfParties.add(editText3.getText().toString());
//        arrayOfParties.add(editText4.getText().toString());
//        arrayOfParties.add(editText6.getText().toString());
//        arrayOfParties.add(editText7.getText().toString());
//
//        Intent intent = new Intent(CreateParty.this, FindParty.class);
//        intent.putExtra("array_list", arrayOfParties);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View view) {
//        passArrayList();
//        if(view == editText) {
//            editText.setText("");
//        } else if(view == editText2){
//            editText2.setText("");
//        } else if(view == editText3){
//            editText3.setText("");
//        }else if(view == editText4){
//            editText4.setText("");
//        }else if(view == editText6){
//            editText6.setText("");
//        }else if(view == editText7){
//            editText7.setText("");
//        }
    }

}
