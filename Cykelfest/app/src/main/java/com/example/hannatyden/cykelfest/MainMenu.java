package com.example.hannatyden.cykelfest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    CurrentParty currentParty;
    ArrayList<PartyItem> partyItems;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.7F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        partyItems = new ArrayList<PartyItem>();


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


   /** Called when the user taps the find party button */
    public void findparty(View view) {
        Intent intent = new Intent(this, FindParty.class);
        view.startAnimation(buttonClick);
        startActivity(intent);
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        System.out.println("Halloj");
//        if (requestCode == 1) {
//            System.out.println("ResultCode = " + resultCode + " Result OK: " + RESULT_OK);
//
//            if (resultCode == RESULT_OK) {
//                ArrayList<String> recieved = data.getStringArrayListExtra("partyArray2");
//                //toSend.add(party);
//                int index = 0;
//                PartyItem item = new PartyItem("1","1","1","1","1","1");
//                for(String s : recieved) {
//                    if(index == 5) {
//                        index = 0;
//                        partyItems.add(item);
//                        item = new PartyItem("1","1","1","1","1","1");
//
//                    }
//
//                    switch (index) {
//                        case 0:
//                            item.setName(s);
//                            break;
//                        case 1:
//                            item.setTheme(s);
//                            break;
//                        case 2:
//                            item.setDate(s);
//                            break;
//                        case 3:
//                            item.setAddress(s);
//                            break;
//                        case 4:
//                            item.setMax(s);
//                            break;
//                        case 5:
//                            item.setEndDate(s);
//                            break;
//                    }
//                    index++;
//
//
//                }
//
//                System.out.println("jag har tagit emot er lista menyn");
//
//
//             //   arrayAdapter.notifyDataSetChanged();
//
//            }
//
//        }}


}
