package com.example.dcyberpanda.upickmobile.BarActivityClasses;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
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


public class MenuAdapter extends ArrayAdapter{

    public static int viewid;
    public static Activity activity;

    int thumbnailIndex;

    List list = new ArrayList();

    public MenuAdapter(Context context, int resource, int thumbnailIndex) {
        super(context,resource);
        this.thumbnailIndex = thumbnailIndex;
    }

    static class DataHandler{
        TextView name;
        TextView price;
        ImageButton btn;
        TextView quantity;
        ImageView thumbnail;
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

        viewid = R.layout.menu_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.bar_item_name);
            handler.price = (TextView) row.findViewById(R.id.bar_item_price);
            handler.btn = (ImageButton) row.findViewById(R.id.bar_add_button);
            handler.quantity = (TextView) row.findViewById(R.id.bar_item_quantity);
            handler.thumbnail = (ImageView) row.findViewById(R.id.bar_item_thumbnail);
            handler.thumbnail = setThumbnailSrc(handler.thumbnail);
            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final MenuItem dataProvider;
        dataProvider = (MenuItem) this.getItem(position);

        handler.name.setText(dataProvider.getName());
        handler.price.setText(dataProvider.getPrice().toString());

        changeQuantityDisplay(handler.quantity,dataProvider);

        handler.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeQuantityDisplay(handler.quantity,addToCart(dataProvider));
            }
        });

        return row;
    }

    private ImageView setThumbnailSrc(ImageView thumbnail){
        switch (thumbnailIndex){
            case 0:
                thumbnail.setImageResource(R.drawable.coffeethumbnail);
                break;
            case 1:
                thumbnail.setImageResource(R.drawable.softdrinkthumbnail);
                break;
            case 2:
                thumbnail.setImageResource(R.drawable.alcoholicthumbnail);
                break;
        }
        return thumbnail;
    }

    private Integer addToCart(MenuItem item){
        for (CartItem cartItem : CartActivity.cartItems){
            if (cartItem.getName().equals(item.getName())){
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return cartItem.getQuantity();
            }
        }

        CartActivity.cartItems.add(new CartItem(item.getName(),item.getPrice(),1));
        return 1;
    }

    private void changeQuantityDisplay(TextView textView,MenuItem menuItem){
        Integer quantity = 0;
        for (CartItem cartItem : CartActivity.cartItems){
            if (cartItem.getName().equals(menuItem.getName())){
                quantity = cartItem.getQuantity();
            }
        }
        changeQuantityDisplay(textView,quantity);
    }

    private void changeQuantityDisplay(TextView textView, Integer quantity){
        if (quantity == 0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        textView.setText(quantity.toString());
    }
}
