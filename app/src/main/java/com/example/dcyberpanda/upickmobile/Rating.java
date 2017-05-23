package com.example.dcyberpanda.upickmobile;

public class Rating {

    String name;
    String comment;
    int rating;

    public Rating(String name, String comment, int rating) {
        this.name = name;
        this.comment = comment;
        this.rating = rating;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

