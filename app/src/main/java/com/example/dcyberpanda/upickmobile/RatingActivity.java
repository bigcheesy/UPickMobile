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
import android.widget.Toast;

import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.RatingAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.dcyberpanda.upickmobile.R.id.rating_viewpager;

public class RatingActivity extends AppCompatActivity {

    public static ArrayList<Rating> ratings;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratings = new ArrayList<>();

        viewPager = (ViewPager) findViewById(rating_viewpager);
        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager(),this);

        viewPager.setAdapter(customAdapter);

        LinearLayout sliderDotspanel = (LinearLayout) findViewById(R.id.rating_sliderdots);

        ViewPagerDots viewPagerDots = new ViewPagerDots(viewPager, customAdapter, sliderDotspanel, getApplicationContext());
        viewPagerDots.setListener();

        ratings.add(new Rating("Kristi", "Lokal qr", 5));
        ratings.add(new Rating("Dami", "Lokal cka", 3));
        ratings.add(new Rating("Sergi", " -Left the group.", 1));
        ratings.add(new Rating("Fabio", "Hajde e shkarkojm me TOR!", 5));
        createList();

        final TextView next = (TextView) findViewById(R.id.rating_publiko);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentItem = viewPager.getCurrentItem();
                if(currentItem == 0){
                    viewPager.setCurrentItem(currentItem + 1);
                }else{
                    Toast.makeText(getApplicationContext(), "Komenti juaj u publikua me sukses", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = viewPager.getCurrentItem();
                if(currentItem == 0){
                    next.setText("Vazhdo");
                }else{
                    next.setText("Publiko");
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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





