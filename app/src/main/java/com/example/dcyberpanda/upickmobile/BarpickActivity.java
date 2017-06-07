package com.example.dcyberpanda.upickmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dcyberpanda.upickmobile.CustomAdapters.BarpickAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;
import com.example.dcyberpanda.upickmobile.R;

import java.util.ArrayList;

public class BarpickActivity extends AppCompatActivity {

    private static ArrayList<Bar> bars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barpick);

        bars = new ArrayList<>();

        bars.add(new Bar("Dine", "Varri i Bomit", 4.5f));
        bars.add(new Bar("Grand Bocca", "Rruga e burgut, perballe shkolles Harry Fultz.", 3f));
        bars.add(new Bar("Mon cheri", "Rruga e kavajes", 5f));

        createList();
    }

    private void createList(){
        ListView listView =(ListView) findViewById(R.id.bar_list);
        BarpickAdapter barpickAdapter = new BarpickAdapter(this, R.layout.bar_item);

        for (Bar bar : bars){
            barpickAdapter.add(bar);
        }

        listView.setAdapter(barpickAdapter);
    }
}
