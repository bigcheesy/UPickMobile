package com.example.dcyberpanda.upickmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CouponActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    public static PurchasedCoupon purchasedCoupon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        startService(new Intent(getBaseContext(), CouponService.class));

        initializeViews();
        final TextView timeLeft = (TextView) findViewById(R.id.full_coupon_time_left);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s = intent.getStringExtra(CouponService.COUPON_MESSAGE);
                if (s != null) {
                    timeLeft.setText(s);
                    if (s.equals("00:00:00")){
                        purchasedCoupon = null;
                        Intent intent2 = new Intent(CouponActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                    }
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CouponActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(CouponService.COUPON_RESULT)
        );
        super.onStart();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
    }

    private void initializeViews(){
        TextView couponPlace = (TextView) findViewById(R.id.full_coupon_place);
        TextView couponDate = (TextView) findViewById(R.id.full_coupon_time);
        TextView couponThumbnail = (TextView) findViewById(R.id.full_coupon_thumbnail);
        TextView couponName = (TextView) findViewById(R.id.full_coupon_name);

        couponPlace.setText(purchasedCoupon.getPurchasePlace());
        couponDate.setText(purchasedCoupon.getPurchaseDate());
        couponThumbnail.setText(purchasedCoupon.getThumbnailText());
        couponName.setText(purchasedCoupon.getName());
    }
}

