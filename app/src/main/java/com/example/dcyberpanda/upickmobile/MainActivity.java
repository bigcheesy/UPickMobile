package com.example.dcyberpanda.upickmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CartActivity.cartItems == null) {
            CartActivity.cartItems = new ArrayList<>();
        }
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

}
