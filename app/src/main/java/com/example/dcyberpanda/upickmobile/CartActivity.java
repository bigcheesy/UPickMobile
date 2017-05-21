package com.example.dcyberpanda.upickmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dcyberpanda.upickmobile.BarActivityClasses.MenuAdapter;
import com.example.dcyberpanda.upickmobile.CustomAdapters.CartAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static ArrayList<CartItem> cartItems;
    private static TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalTextView = (TextView) findViewById(R.id.cart_total);
        createCartList();
        calculateTotal();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CartActivity.this, BarActivity.class);
        startActivity(intent);
        finish();
    }

    private void createCartList(){
        ListView listView =(ListView) findViewById(R.id.cart_list);
        CartAdapter menuAdapter = new CartAdapter(this, R.layout.menu_item);

        for (CartItem item : cartItems){
            menuAdapter.add(item);
        }

        listView.setAdapter(menuAdapter);
    }

    public static void calculateTotal(){
        Integer sum = 0;
        for (CartItem item: cartItems){
            sum += item.getPrice() * item.getQuantity();
        }
        totalTextView.setText(sum.toString());
    }
}
