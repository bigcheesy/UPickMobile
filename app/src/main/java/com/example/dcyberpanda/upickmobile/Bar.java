package com.example.dcyberpanda.upickmobile;

/**
 * Created by D'CyberPanda on 6/7/2017.
 */

public class Bar {
    private String name;
    private String address;
    private float rating;

    public Bar(String name, String address, float rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
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
}
