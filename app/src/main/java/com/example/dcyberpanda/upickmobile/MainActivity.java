package com.example.dcyberpanda.upickmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void click(View v){
        Toast.makeText(this,"click",Toast.LENGTH_SHORT);
    }

    public void bar(View v){
        Intent intent = new Intent(MainActivity.this, BarActivity.class);
        startActivity(intent);
        finish();
    }
}
