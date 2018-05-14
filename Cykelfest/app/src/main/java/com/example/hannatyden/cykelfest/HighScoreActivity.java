package com.example.hannatyden.cykelfest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    int counter = 1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        ArrayList<HighScoreItem> arrayOfUsers = new ArrayList<HighScoreItem>();
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
        listView = (ListView) findViewById(R.id.highscoreList);
        listView.setAdapter(adapter);

        ArrayList<HighScoreItem> newScores = new ArrayList<HighScoreItem>();
        newScores.add(new HighScoreItem("Kebabfesten",300 ));
        newScores.add(new HighScoreItem("Barnkalaset", 200));
        newScores.add(new HighScoreItem("Swagfest", 420));

        Collections.sort(newScores,new Comparator<HighScoreItem>(){
            @Override
            public int compare(final HighScoreItem lhs,HighScoreItem rhs) {
                if (rhs.getScore() > lhs.getScore()){
                    return 1;
                } else if (rhs.getScore() < lhs.getScore()){
                    return -1;
                }
                return 0;
                //TODO return 1 if rhs should be before lhs
                //     return -1 if lhs should be before rhs
                //     return 0 otherwise
            }
        });

        final ImageButton ib = (ImageButton) findViewById(R.id.imageButtonInfo);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HighScoreActivity.this, PopHighScore.class));
                ib.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });

        adapter.addAll(newScores);


    }


    public class UsersAdapter extends ArrayAdapter<HighScoreItem> {
        public UsersAdapter(Context context, ArrayList<HighScoreItem> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            HighScoreItem item = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_name, parent, false);
            }
            // Lookup view for data population
            TextView tvPosition = (TextView) convertView.findViewById(R.id.tvPosition);
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvHome = (TextView) convertView.findViewById(R.id.tvScore);
            // Populate the data into the template view using the data object
            tvPosition.setText(Integer.toString(counter));
            counter++;
            tvName.setText(item.name);
            tvHome.setText(Integer.toString(item.score));
            // Return the completed view to render on screen
            return convertView;
        }
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
