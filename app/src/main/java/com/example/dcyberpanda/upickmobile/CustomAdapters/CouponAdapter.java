package com.example.dcyberpanda.upickmobile.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dcyberpanda.upickmobile.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by D'CyberPanda on 7/23/2016.
 */


public class CouponAdapter extends ArrayAdapter{

    public static int viewid;


    List list = new ArrayList();

    public CouponAdapter(Context context, int resource) {
        super(context,resource);
    }

    static class DataHandler{
        TextView name;
        TextView description;
        TextView thumbnail;
        TextView purchaseButton;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return this.list.size();
    }

    @Override
    public Object getItem(int position){
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        viewid = R.layout.coupon_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.coupon_name);
            handler.description = (TextView) row.findViewById(R.id.coupon_description);
            handler.thumbnail = (TextView) row.findViewById(R.id.coupon_thumbnail);
            handler.purchaseButton = (TextView) row.findViewById(R.id.coupon_purchase);

            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final Coupon coupon;
        coupon = (Coupon) this.getItem(position);

        handler.name.setText(coupon.getName());
        handler.description.setText(coupon.getDescription());
        handler.thumbnail.setText(coupon.getThumbnailText());
        handler.purchaseButton.setText(coupon.getCost().toString() + " \nPike");
        handler.purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyCoupon(coupon);
            }
        });

        return row;
    }

    private void buyCoupon(Coupon coupon){
        CouponActivity.purchasedCoupon = new PurchasedCoupon(coupon);
        Intent intent = new Intent(getContext(), CouponActivity.class);
        getContext().startActivity(intent);
    }

}
