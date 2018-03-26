/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.io.Serializable;
import java.util.GregorianCalendar;


/**
 *
 * @author lev&Rotem
 */
public final class User implements Serializable{
    
    
    String name;
    GregorianCalendar time;
    int moves;
    int score;
    int hints;

    public int getHints() {
        return hints;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }

    public User(String name, GregorianCalendar time, int moves,int hints) {
        setName(name) ;
        setTime(time);
        setMoves(moves);
        setHints(hints);
        setScore(score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
