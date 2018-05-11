package com.example.hannatyden.cykelfest;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FindParty extends AppCompatActivity {
    //private static final String[]paths = {"Vinter", "Vår", "Sommar"};
    //private ArrayList<String> toSend = new ArrayList<>();
    //private ArrayList<String> parties = new ArrayList<>();
    private ArrayList<PartyItem> parties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_party);

        //collect our intent
        Intent intent = getIntent();
        PartyItem partyItem = intent.getParcelableExtra("Party");

        if(partyItem == null){
            Log.i("test","Hallå");
        }
//        now collect all property values
//        String theme = partyItem.getTheme();
//        String name = partyItem.getName();
//        String date = partyItem.getDate();
//        String address = partyItem.getAddress();
//        String max = partyItem.getMax();
//        String endDate = partyItem.getEndDate();

//        Bundle b = getIntent().getExtras();
//        for(int i = 0; i < 6; i++){
//            if (null != b) {
//                String tema = b.getString("Tema");
//                ArrayList<String> arrayOfParties = b.getStringArrayList("array_list");
//
//                Log.i("List", "Passed Array List :: " + arrayOfParties);
//                System.out.print(arrayOfParties);
//            }
//        }
        
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




    //FindParty.UsersAdapter adapter = new FindParty.UsersAdapter(this, arrayOfParties);

//    private static ArrayList<PartyItem> arrayOfParties = new ArrayList<>();
//
//    public static void addItem(PartyItem item) {
//        arrayOfParties.add(item);
//    }
//
//    public static ArrayList<PartyItem> getData() {
//        return arrayOfParties;
//    }


//    public class UsersAdapter extends ArrayAdapter<PartyItem> {
//        public UsersAdapter(Context context, ArrayList<PartyItem> party) {
//            super(context, 0, party);
//        }
//    }
}
