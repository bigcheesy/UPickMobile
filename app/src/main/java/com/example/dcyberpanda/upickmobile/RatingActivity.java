package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.RatingAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.dcyberpanda.upickmobile.R.id.rating_viewpager;

public class RatingActivity extends AppCompatActivity {

    public static ArrayList<Rating> ratings;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratings = new ArrayList<>();
        viewPager = (ViewPager) findViewById(rating_viewpager);

        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager(),this);
        dotscount = customAdapter.getCount();
        dots = new ImageView[dotscount];
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        for(int i = 0;  i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pikjoaktive));
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pik));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i < dotscount; i++){

                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pikjoaktive));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pik));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        ratings.add(new Rating("Kristi", "Lokal qr", 5));
        ratings.add(new Rating("Landi", "Lokal cka", 3));
        ratings.add(new Rating("Tedi", "lokal qr", 5));
        ratings.add(new Rating("Fabio", "lokal vk", 1));
        createList();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RatingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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





