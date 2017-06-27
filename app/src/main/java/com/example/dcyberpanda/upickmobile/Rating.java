package com.example.dcyberpanda.upickmobile;

public class Rating {

    String name;
    String comment;
    int rating;
    int userId;

    public Rating(int userId ,String name, String comment, int rating) {
        this.name = name;
        this.comment = comment;
        this.rating = rating;
        this.userId = userId;


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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

