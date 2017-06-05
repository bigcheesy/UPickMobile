package com.example.dcyberpanda.upickmobile;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by D'CyberPanda on 6/2/2017.
 */

public class CouponService extends Service {

    public static final String COUPON_MESSAGE = "message sent boiiis";
    public static final String COUPON_RESULT = "result boiiis";
    //private static final long COUPON_LIFETIME = 10800000;
    private static final long COUPON_LIFETIME = 10000;

    LocalBroadcastManager broadcastManager;


    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        broadcastManager = LocalBroadcastManager.getInstance(this);
        runService();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void runService(){
        new CountDownTimer(COUPON_LIFETIME,1000) {

            public void onTick ( long millisUntilFinished){
                sendResult(convertToDate(millisUntilFinished));
            }

            public void onFinish() {
                sendResult("00:00:00");
                CouponActivity.purchasedCoupon = null;

                CouponService.this.stopSelf();
            }
        }.start();
    }


    private String convertToDate(long millis){
        Date date = new Date(millis);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateFormatted = formatter.format(date);

        return dateFormatted;
    }

    public void sendResult(String message) {
        Intent intent = new Intent(COUPON_RESULT);
        if(message != null)
            intent.putExtra(COUPON_MESSAGE, message);
        broadcastManager.sendBroadcast(intent);
    }
}
