package com.example.dcyberpanda.upickmobile;

import java.util.InputMismatchException;

/**
 * Created by D'CyberPanda on 5/29/2017.
 */

public class Coupon {

    private String name;
    private String description;
    private String thumbnailText;
    private Integer cost;

    public Coupon(String name, String description, String thumbnailText, Integer cost){
        this.name = name;
        this.description = description;
        this.thumbnailText = thumbnailText;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailText() {
        return thumbnailText;
    }

    public void setThumbnailText(String thumbnailText) {
        this.thumbnailText = thumbnailText;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
