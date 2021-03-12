package com.server2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Score list.
 */
public class ScoreList {
    /**
     * The Scores.
     */
    ArrayList<Score> scores = new ArrayList<>();

    /**
     * Instantiates a new Score list.
     *
     * @param scores2 the scores 2
     */
    ScoreList(  ArrayList<Score> scores2  )
    {
        this.scores = scores2;
    }

    /**
     * Sort.
     */
    public void sort(){
        int i, j;
        for (Score sc:scores){
            for (j = 0; j < scores.size() - 1; j++){
               if( compareScore(scores.get(j), scores.get(j+1))){
                   Collections.swap(scores, j, j+1);
                }
            }
        }
        for(Score sc:scores){
            sc.printScore();
        }
    }

    /**
     * Print score list string.
     *
     * @return the string
     */
    public String printScoreList(){
        String temp = "";
        for(int i = 0; i<scores.size() && i<5; i++)
        {
           temp += scores.get(i).printScore();
        }
        return temp;
    }

    /**
     * Compare score boolean.
     *
     * @param score1 the score 1
     * @param score2 the score 2
     * @return the boolean
     */
    public boolean compareScore(Score score1, Score score2){
       // System.out.println(score1.getWynik() + " " + score2.getWynik());
        if(score1.getWynik() <= score2.getWynik()){
            return true;
        }
        else
            return false;
    }
}
