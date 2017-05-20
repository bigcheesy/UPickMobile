package com.example.dcyberpanda.upickmobile.BarActivityClasses;

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
        DataHandler handler;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.menu_item_name);
            handler.price = (TextView) row.findViewById(R.id.menu_item_price);
            handler.btn = (ImageButton) row.findViewById(R.id.menu_add_button);
            handler.thumbnail = (ImageView) row.findViewById(R.id.menu_thumbnail);
            handler.thumbnail = setThumbnailSrc(handler.thumbnail);
            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }


        final MenuItem dataProvider;
        dataProvider = (MenuItem) this.getItem(position);

        handler.name.setText(dataProvider.getName());
        handler.price.setText(dataProvider.getPrice().toString());


        handler.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Click",Toast.LENGTH_SHORT).show();
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
}
