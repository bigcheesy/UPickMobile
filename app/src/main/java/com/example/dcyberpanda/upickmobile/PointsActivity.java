package com.example.dcyberpanda.upickmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dcyberpanda.upickmobile.BarActivityClasses.MenuAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.CouponAdapter;

import java.util.ArrayList;

public class PointsActivity extends AppCompatActivity {

    private ArrayList<Coupon> coupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        coupons = new ArrayList<>();
        addCoupons();
        createList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PointsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createList(){
        ListView listView =(ListView) findViewById(R.id.coupon_list);
        CouponAdapter couponAdapter = new CouponAdapter(this, R.layout.coupon_item);

        for (Coupon coupon : coupons){
            couponAdapter.add(coupon);
        }

        listView.setAdapter(couponAdapter);
    }

    private void addCoupons(){
        coupons.add(new Coupon("Kupon 80L", "Me kete kupon mund te bleni nje pije deri ne 80L", "80",5000));
        coupons.add(new Coupon("Kupon 20%", "Me kete kupon mund te merrni 20% ulje", "20%",7500));
        coupons.add(new Coupon("Kupon 150L", "Me kete kupon mund te bleni nje pije deri ne 150L", "150",10000));
        coupons.add(new Coupon("Kupon 500L", "Me kete kupon mund te bleni nje pije deri ne 500L", "500",30000));
    }
}
