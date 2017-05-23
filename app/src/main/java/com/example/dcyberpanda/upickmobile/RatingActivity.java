package com.example.dcyberpanda.upickmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.RatingAdapter;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {

    public static ArrayList<Rating> ratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratings = new ArrayList<>();
        ratings.add(new Rating("Kristi", "Lokal qr", 5));
        ratings.add(new Rating("Dami", "Lokal eh", 3));
        ratings.add(new Rating("Samir", "SUPER APP DO E BLEJ PATJETER!!!!!!", 5));
        ratings.add(new Rating("Fabio", "Hajde shkarkojm me tor", 1));
        createList();
    }

    private void createList(){
        ListView listView = (ListView) findViewById(R.id.rating_list);

        RatingAdapter ratingAdapter = new RatingAdapter(this, R.layout.rating_item);

        for (Rating rating : ratings){
            ratingAdapter.add(rating);
        }

        listView.setAdapter(ratingAdapter);


    }
}




