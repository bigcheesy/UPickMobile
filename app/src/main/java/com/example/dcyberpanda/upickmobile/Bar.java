package com.example.dcyberpanda.upickmobile;

/**
 * Created by D'CyberPanda on 6/7/2017.
 */

public class Bar {
    private String name;
    private String address;
    private float rating;
    private String drawableid;
    private String dbanme;

    public Bar(String name, String address, float rating, String drawableid, String dbname) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.drawableid = drawableid;
        this.dbanme = dbname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDrawableid() {
        return drawableid;
    }

    public void setDrawableid(String drawableid) {
        this.drawableid = drawableid;
    }

    public String getDbanme() {
        return dbanme;
    }

    public void setDbanme(String dbanme) {
        this.dbanme = dbanme;
    }
}
