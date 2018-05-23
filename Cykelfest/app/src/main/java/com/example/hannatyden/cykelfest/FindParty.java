package com.example.hannatyden.cykelfest;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FindParty extends AppCompatActivity {
    ListView l;
    List<String> parties;
    ArrayList<String> party;
    ArrayList<PartyItem> partyItems;
    ArrayAdapter<String> arrayAdapter;
    boolean clickedButton;

    /** Called when the user taps the Create party button */
    public void createparty(View view) {
        Intent intent = new Intent(this, CreateParty.class);
        startActivityForResult(intent, 1);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_party);
        clickedButton = false;


        l = findViewById(R.id.list);
        parties = new ArrayList<String>();
        System.out.println(parties);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parties);
        l.setAdapter(arrayAdapter);
        if(partyItems == null) {
            partyItems = new ArrayList<PartyItem>();
        }

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // HÅRDKODAD ATT SKICKA ENBART EN STRÄNG FRÅN SENAST TILLAGDA NOTISEN. FUNKAR BARA FÖR 1 NOTIS
            Intent intent = new Intent(getApplicationContext(), PartyInfoPopUp.class);
            intent.putExtra("1", partyItems.get(position).name);
            intent.putExtra("2", partyItems.get(position).theme);
            intent.putExtra("3", partyItems.get(position).date);
            intent.putExtra("4", partyItems.get(position).address);
            intent.putExtra("5", partyItems.get(position).max);
            intent.putExtra("6", partyItems.get(position).endDate);
            intent.putExtra("7", partyItems.get(position).attending);

            startActivityForResult(intent, 2);
        }
        });

        TextView tv = (TextView) findViewById(R.id.textview);


        partyItems.add(new PartyItem("Vinter", "Vinterfesten", "181011", "Gatan 1", "50", "181001", "no"));
        parties.add(partyItems.get(0).name);


    }

    @Override
    protected void onPause(){
        super.onPause();



//        if(clickedButton) {
//            Intent intent = new Intent();
//            ArrayList<String> send = new ArrayList<String>();
//            for (PartyItem p : partyItems) {
//                send.add(p.name);
//                send.add(p.theme);
//                send.add(p.date);
//                send.add(p.address);
//                send.add(p.max);
//                send.add(p.endDate);
//            }
//            intent.putStringArrayListExtra("partyArray2", send);
//            setResult(RESULT_OK, intent);
//
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("BEFORE: ON-ACTIVITY-RESULT FOR party ACTIVITY");

        if (requestCode == 1) {
            System.out.println("ResultCode = " + resultCode + " Result OK: " + RESULT_OK);

            if (resultCode == RESULT_OK) {
                party = data.getStringArrayListExtra("partyArray");
                PartyItem item = new PartyItem(party.get(0),party.get(1), party.get(2), party.get(3), party.get(5), party.get(4), "yes");
                partyItems.add(item);
                System.out.println("jag har tagit emot er lista");

                for(PartyItem p : partyItems) {
                    if(!parties.contains(p.name)){
                        parties.add(p.name);
                    }


                }

                arrayAdapter.notifyDataSetChanged();

            }
        } else if (requestCode == 2) {
            System.out.println("Recieved request 2");
            System.out.println(resultCode);
            if (resultCode == RESULT_OK) {

                String a = data.getStringExtra("1");
                String b = data.getStringExtra("2");
                System.out.println(a);
                System.out.println(b);
                for (PartyItem p : partyItems) {
                    if (p.getName().equals(b)) {
                        p.setAttending(a);
                        System.out.println(p.getName());


                    }
                }

            }
        }
    }

    public void enterNotificationActivity(View view) {
        Intent intent = new Intent(FindParty.this, CreateParty.class);
        startActivityForResult(intent, 1);
    }



//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        if(requestCode == 1){
//            if(resultCode == RESULT_OK){
//                ArrayList<String> partyinfo = data.getStringArrayListExtra("PartyArray");
//                toSend.add(partyinfo);
//                parties.add(partyinfo.get(0));
//                arrayAdapter.notifyDataSetChanged();
//
//            }
//        }
//    }

}
