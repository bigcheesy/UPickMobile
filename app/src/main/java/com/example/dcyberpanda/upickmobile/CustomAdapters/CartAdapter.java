package com.example.dcyberpanda.upickmobile.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcyberpanda.upickmobile.MenuItem;
import com.example.dcyberpanda.upickmobile.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by D'CyberPanda on 7/23/2016.
 */


public class CartAdapter extends ArrayAdapter{

    public static int viewid;


    List list = new ArrayList();

    public CartAdapter(Context context, int resource) {
        super(context,resource);
    }

    static class DataHandler{
        TextView name;
        TextView price;
        TextView quantity;
        ImageButton btnadd;
        ImageButton btnremove;
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

        viewid = R.layout.cart_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.cart_name);
            handler.price = (TextView) row.findViewById(R.id.cart_price);
            handler.quantity = (TextView) row.findViewById(R.id.cart_quantity);
            handler.btnadd = (ImageButton) row.findViewById(R.id.cart_add);
            handler.btnremove = (ImageButton) row.findViewById(R.id.cart_remove);
            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final CartItem cartItem;
        cartItem = (CartItem) this.getItem(position);

        handler.name.setText(cartItem.getName());
        handler.price.setText(cartItem.getPrice().toString());
        handler.quantity.setText(cartItem.getQuantity().toString());

        handler.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItem.getQuantity() < 99){
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    handler.quantity.setText(cartItem.getQuantity().toString());
                    CartActivity.calculateTotal();
                }
            }
        });

        handler.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItem.getQuantity() > 0){
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    handler.quantity.setText(cartItem.getQuantity().toString());
                    CartActivity.calculateTotal();
                }
            }
        });
        return row;
    }

}
