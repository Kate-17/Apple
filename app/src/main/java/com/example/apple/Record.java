package com.example.apple;

/**
 * Created by Таня on 03.12.2017.
 */

public class Record {
    public int id;
    public String nameOfPlayer;
    public String numberOfAttempt;
    public String time;


    public Record(){}


    public Record(int id, String nameOfPlayer, String numberOfAttempt, String time) {
        this.id = id;
        this.nameOfPlayer = nameOfPlayer;
        this.numberOfAttempt = numberOfAttempt;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberOfAttempt(String numberOfAttempt) {
        this.numberOfAttempt = numberOfAttempt;
    }


    public void setnameOfPlayer(String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
    }

    public void setTime(String time) {
        this.time= time;
    }

    public int getId() {
        return id;
    }
    public String getNumberOfAttempt() {
        return numberOfAttempt;
    }
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }
    public String getTime() {
        return time;
    }

}