package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;

import com.example.dcyberpanda.upickmobile.BarActivityClasses.MenuAdapter;
import com.example.dcyberpanda.upickmobile.BarActivityClasses.MenuFragment;

import java.util.ArrayList;

public class BarActivity extends AppCompatActivity {
    public static final String BAR_ITEMS_INDEX = "baritems";
    public static final String THUMBNAIL_INDEX = "thumbindex";

    TabLayout tabLayout;
    ViewPager viewPager;
    public static ArrayList<ArrayList<MenuItem>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        if (items == null) {
            items = new ArrayList<>();
        }

        viewPager = (ViewPager) findViewById(R.id.bar_pager);
        tabLayout = (TabLayout) findViewById(R.id.bar_tabs);

        if (items.isEmpty()) {
            DatabaseConnection.getMenu(this, new DatabaseConnection.VolleyCallback() {
                @Override
                public void onSuccess(Object result) {
                    ArrayList arrayResult = (ArrayList) result;
                    String previousCategory = ((MenuItem) arrayResult.get(0)).getCategory();
                    ArrayList<MenuItem> tempArray = new ArrayList<>();
                    for (Object object : arrayResult) {
                        MenuItem item = (MenuItem) object;
                        if (!item.getCategory().equals(previousCategory)) {
                            previousCategory = item.getCategory();
                            items.add(tempArray);
                            tempArray = new ArrayList<>();
                        }
                        tempArray.add(item);
                    }
                    items.add(tempArray);
                    initializeTabs();
                }
            });
        }else{
            initializeTabs();
        }
    }

    private void initializeTabs(){
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    public void openCart(View v){
        Intent intent = new Intent(BarActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String[] titles = new String[items.size()];
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
            for (int i = 0; i < titles.length;i++){
                titles[i] = items.get(i).get(0).getCategory();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(items.get(position),titles[position]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return titles[position];
        }
    }

    private Fragment createFragment(ArrayList<MenuItem> items,String category){
        MenuFragment fragment = new MenuFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(BAR_ITEMS_INDEX,items);
        args.putString(THUMBNAIL_INDEX,category);
        fragment.setArguments(args);

        return fragment;
    }
}
