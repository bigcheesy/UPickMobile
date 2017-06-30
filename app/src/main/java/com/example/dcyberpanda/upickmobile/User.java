package com.example.dcyberpanda.upickmobile;

/**
 * Created by D'CyberPanda on 6/30/2017.
 */

public class User {
    public int userid;
    public String name;
    public String surname;

    public Integer points;

    public User(int userid, String name, String surname, Integer points) {
        this.userid = userid;
        this.name = name;
        this.surname = surname;
        this.points = points;
    }
}
