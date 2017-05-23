package com.example.dcyberpanda.upickmobile.CustomAdapters;

import android.content.Context;
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


public class RatingAdapter extends ArrayAdapter{

    public static int viewid;


    List list = new ArrayList();

    public RatingAdapter(Context context, int resource) {
        super(context,resource);
    }

    static class DataHandler{
        TextView name;
        TextView comment;
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

        viewid = R.layout.rating_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.rating_title);
            handler.comment = (TextView) row.findViewById(R.id.rating_comment);

            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final Rating rating;
        rating = (Rating) this.getItem(position);

        handler.name.setText(rating.getName());
        handler.comment.setText(rating.getComment());


        return row;
    }

}
