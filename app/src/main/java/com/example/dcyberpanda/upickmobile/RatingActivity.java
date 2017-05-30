package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.RatingAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {

    public static ArrayList<Rating> ratings;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratings = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.rating_viewpager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        ratings.add(new Rating("Kristi", "Lokal qr", 5));
        ratings.add(new Rating("Dami", "Lokal eh", 3));
        ratings.add(new Rating("Samir", "SUPER APP DO E BLEJ PATJETER!!!!!!", 5));
        ratings.add(new Rating("Fabio", "Hajde e shkarkojm me tor", 1));
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


    private class CustomAdapter extends FragmentPagerAdapter {
        int fragmentNumber = 2;
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                default:
                    return new Fragment1();
            }
        }

        @Override
        public int getCount() {
            return fragmentNumber;
        }


    }
}





