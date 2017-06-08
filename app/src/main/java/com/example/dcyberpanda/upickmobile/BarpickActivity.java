package com.example.dcyberpanda.upickmobile;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dcyberpanda.upickmobile.CustomAdapters.BarpickAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.R;

import java.util.ArrayList;

public class BarpickActivity extends AppCompatActivity {

    private static ArrayList<Bar> bars;
    private BarpickAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barpick);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bars = new ArrayList<>();

        bars.add(new Bar("Dine", "Varri i Bomit", 4.5f));
        bars.add(new Bar("Grand Bocca", "Rruga e burgut, perballe shkolles Harry Fultz.", 3f));
        bars.add(new Bar("Mon cheri", "Rruga e kavajes", 5f));

        createList();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_menu));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.isEmpty()) {
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void createList(){
        ListView listView =(ListView) findViewById(R.id.bar_list);
        adapter = new BarpickAdapter(BarpickActivity.this, bars);
        listView.setAdapter(adapter);
    }
}
