package com.example.dcyberpanda.upickmobile;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by D'CyberPanda on 6/1/2017.
 */

public class PurchasedCoupon extends Coupon{

    private String purchasePlace;
    private String purchaseDate;

    public PurchasedCoupon(Coupon coupon) {
        super(coupon.getName(), coupon.getDescription(), coupon.getThumbnailText(), coupon.getCost());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        purchaseDate = simpleDateFormat.format(calendar.getTime());

        purchasePlace = "Grand Bocca"; //placeholder
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
