package com.example.dcyberpanda.upickmobile.BarActivityClasses;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dcyberpanda.upickmobile.BarActivity;
import com.example.dcyberpanda.upickmobile.MenuItem;
import com.example.dcyberpanda.upickmobile.R;

import java.util.ArrayList;

/**
 * Created by D'CyberPanda on 5/20/2017.
 */
public class MenuFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int thumbnailIndex = getArguments().getInt(BarActivity.THUMBNAIL_INDEX);
        ArrayList<MenuItem> items = getArguments().getParcelableArrayList(BarActivity.BAR_ITEMS_INDEX);
        // Inflate the layout for this fragment
        //This layout contains your list view
        View view = inflater.inflate(R.layout.menu_list, container, false);


        ListView listView =(ListView)view.findViewById(R.id.bar_menu);
        MenuAdapter menuAdapter = new MenuAdapter(getActivity(), R.layout.menu_item,thumbnailIndex);

        for (MenuItem item : items){
            menuAdapter.add(item);
        }

        listView.setAdapter(menuAdapter);

        return view;
    }
}
