package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dcyberpanda.upickmobile.BarActivityClasses.MenuFragment;
import com.example.dcyberpanda.upickmobile.CustomAdapters.BarpickAdapter;

import java.util.ArrayList;

import static com.example.dcyberpanda.upickmobile.R.id.main_pager;
import static com.example.dcyberpanda.upickmobile.R.id.rating_viewpager;

public class MainActivity extends AppCompatActivity {

    public static final String OFFER_INDEX = "offerindex";

    public static String CURRENT_BAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        String barDB = getIntent().getStringExtra(BarpickAdapter.DBNAME_INDEX);

        if (barDB != null){
            CURRENT_BAR = barDB;
            BarActivity.items = new ArrayList<>();
            CartActivity.cartItems = new ArrayList<>();
            OfferCache.offerSrcs = new ArrayList<>();
            DatabaseConnection.getOffers(this.getApplicationContext(), new DatabaseConnection.VolleyCallback() {
                @Override
                public void onSuccess(Object result) {
                    ArrayList<String> resultArray = (ArrayList<String>) result;
                    for (String str : resultArray){
                        OfferCache.offerSrcs.add(str);
                    }
                    initializeViews();
                }
            });
        }else{
            initializeViews();
        }
    }

    private void initializeViews(){
        if (CartActivity.cartItems == null) {
            CartActivity.cartItems = new ArrayList<>();
        }

        ViewPager viewPager = (ViewPager) findViewById(main_pager);
        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager(),this);

        viewPager.setAdapter(customAdapter);
        viewPager.setOffscreenPageLimit(customAdapter.getCount());

        LinearLayout sliderDotspanel = (LinearLayout) findViewById(R.id.main_sliderdots);

        ViewPagerDots viewPagerDots = new ViewPagerDots(viewPager, customAdapter, sliderDotspanel, getApplicationContext());
        viewPagerDots.setListener();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, BarpickActivity.class);
        startActivity(intent);
        finish();
    }

    public void bar(View v){
        Intent intent = new Intent(MainActivity.this, BarActivity.class);
        startActivity(intent);
        finish();
    }

    public void ratings(View v){
        Intent intent = new Intent(MainActivity.this, RatingActivity.class);
        startActivity(intent);
        finish();
    }

    public void points(View v){
        Intent intent;

        if (CouponActivity.purchasedCoupon != null){
            intent = new Intent(MainActivity.this, CouponActivity.class);
        }else{
            intent = new Intent(MainActivity.this, PointsActivity.class);
        }

        startActivity(intent);
        finish();
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        int fragmentNumber = OfferCache.offerSrcs.size();

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(position);
        }

        @Override
        public int getCount() {
            return fragmentNumber;
        }
    }

    private Fragment createFragment(int position){
        OfferFragment fragment = new OfferFragment();

        Bundle args = new Bundle();
        args.putInt(OFFER_INDEX,position);
        fragment.setArguments(args);

        return fragment;
    }
}
