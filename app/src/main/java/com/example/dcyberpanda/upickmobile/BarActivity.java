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
import android.view.Menu;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);


        viewPager = (ViewPager) findViewById(R.id.bar_pager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.bar_tabs);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String[] titles = {"Te ngrohta", "Freskuese", "Alkolike"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return createFragment(getItemsWarm(),position);
                case 1:
                    return createFragment(getItemsCold(),position);
                case 2:
                    return createFragment(getItemsAlcoholic(),position);
                default:
                    return createFragment(getItemsWarm(),0);
            }
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

    private Fragment createFragment(ArrayList<MenuItem> items,int position){
        MenuFragment fragment = new MenuFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(BAR_ITEMS_INDEX,items);
        args.putInt(THUMBNAIL_INDEX,position);
        fragment.setArguments(args);

        return fragment;
    }

    private ArrayList<MenuItem> getItemsWarm(){
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Kafe",60));
        items.add(new MenuItem("Caj",60));
        items.add(new MenuItem("Makiato",80));
        items.add(new MenuItem("Kapucino",120));
        items.add(new MenuItem("Cokollate e ngrohte",150));
        items.add(new MenuItem("Kafe",60));
        items.add(new MenuItem("Caj",60));
        items.add(new MenuItem("Makiato",80));
        items.add(new MenuItem("Kapucino",120));
        items.add(new MenuItem("Cokollate e ngrohte",150));
        items.add(new MenuItem("Kafe",60));
        items.add(new MenuItem("Caj",60));
        items.add(new MenuItem("Makiato",80));
        items.add(new MenuItem("Kapucino",120));
        items.add(new MenuItem("Cokollate e ngrohte",150));

        return items;
    }

    private ArrayList<MenuItem> getItemsCold(){
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Coca Cola",60));
        items.add(new MenuItem("Caj i ftohte",60));
        items.add(new MenuItem("Makiato me akull",80));
        items.add(new MenuItem("Kapucino fredo",120));
        items.add(new MenuItem("Cokollate e ftohte",150));
        items.add(new MenuItem("Coca Cola",60));
        items.add(new MenuItem("Caj i ftohte",60));
        items.add(new MenuItem("Makiato me akull",80));
        items.add(new MenuItem("Kapucino fredo",120));
        items.add(new MenuItem("Cokollate e ftohte",150));
        items.add(new MenuItem("Coca Cola",60));
        items.add(new MenuItem("Caj i ftohte",60));
        items.add(new MenuItem("Makiato me akull",80));
        items.add(new MenuItem("Kapucino fredo",120));
        items.add(new MenuItem("Cokollate e ftohte",150));

        return items;
    }

    private ArrayList<MenuItem> getItemsAlcoholic(){
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Raki",100));
        items.add(new MenuItem("Jack Daniels",700));
        items.add(new MenuItem("Amaro montenegro",500));
        items.add(new MenuItem("Jagermeister",500));
        items.add(new MenuItem("Vodka",500));
        items.add(new MenuItem("Raki",100));
        items.add(new MenuItem("Jack Daniels",700));
        items.add(new MenuItem("Amaro montenegro",500));
        items.add(new MenuItem("Jagermeister",500));
        items.add(new MenuItem("Vodka",500));
        items.add(new MenuItem("Raki",100));
        items.add(new MenuItem("Jack Daniels",700));
        items.add(new MenuItem("Amaro montenegro",500));
        items.add(new MenuItem("Jagermeister",500));
        items.add(new MenuItem("Vodka",500));

        return items;
    }
}
