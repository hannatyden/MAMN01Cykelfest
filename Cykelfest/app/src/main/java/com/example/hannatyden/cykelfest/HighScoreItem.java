package com.example.hannatyden.cykelfest;

/**
 * Created by sanimesic on 2018-04-26.
 */

public class HighScoreItem {
    public String name;
    public Integer score;

    public HighScoreItem(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public Integer getScore(){
        return score;
    }
}

