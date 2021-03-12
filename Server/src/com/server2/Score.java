package com.server2;

import java.util.Scanner;

/**
 * The type Score.
 */
public class Score {
    /**
     * Gets wynik.
     *
     * @return the wynik
     */
    public int getWynik() {
        return wynik;
    }

    /**
     * The Wynik.
     */
    int wynik;
    /**
     * The Name.
     */
    String name;

    /**
     * Instantiates a new Score.
     *
     * @param name2  the name 2
     * @param wynik2 the wynik 2
     */
    public Score(String name2, int wynik2){
        this.name = name2;
        this.wynik = wynik2;
    }

    /**
     * Print score string.
     *
     * @return the string
     */
    public String printScore(){
        return name +" "+ wynik +";";
    }

}
