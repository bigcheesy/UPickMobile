package com.example.dcyberpanda.upickmobile.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcyberpanda.upickmobile.Bar;
import com.example.dcyberpanda.upickmobile.BarActivity;
import com.example.dcyberpanda.upickmobile.CartActivity;
import com.example.dcyberpanda.upickmobile.CartItem;
import com.example.dcyberpanda.upickmobile.MainActivity;
import com.example.dcyberpanda.upickmobile.R;

import java.util.ArrayList;
import java.util.List;

public class BarpickAdapter extends ArrayAdapter {

    public static int viewid;


    List list = new ArrayList();

    public BarpickAdapter(Context context, int resource) {
        super(context,resource);
    }

    static class DataHandler{
        TextView name;
        TextView address;
        RatingBar rating;
        ImageView imageView;
        CardView cardView;
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

        viewid = R.layout.bar_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.bar_name);
            handler.address = (TextView) row.findViewById(R.id.bar_address);
            handler.cardView = (CardView) row.findViewById(R.id.bar_cardview);
            handler.imageView = (ImageView) row.findViewById(R.id.bar_image);
            handler.rating = (RatingBar) row.findViewById(R.id.bar_rating);
            switch (position){
                case 0:
                    handler.imageView.setImageResource(R.drawable.barplaceholder1);
                    break;
                case 1:
                    handler.imageView.setImageResource(R.drawable.barplaceholder2);
                    break;
                case 2:
                    handler.imageView.setImageResource(R.drawable.barplaceholder3);
                    break;
                default:
                    break;
            }
            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final Bar bar;
        bar = (Bar) this.getItem(position);

        handler.name.setText(bar.getName());
        handler.address.setText(bar.getAddress());
        handler.rating.setRating(bar.getRating());

        handler.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(getContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });
        return row;
    }

}