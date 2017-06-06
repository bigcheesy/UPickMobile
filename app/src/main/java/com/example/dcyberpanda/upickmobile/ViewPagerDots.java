package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by D'CyberPanda on 6/6/2017.
 */

public class ViewPagerDots {

    private ViewPager viewPager;
    private FragmentPagerAdapter customAdapter;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private Context context;

    public ViewPagerDots(ViewPager viewPager, FragmentPagerAdapter customAdapter, LinearLayout sliderDotspanel, Context context){
        this.viewPager = viewPager;
        this.customAdapter = customAdapter;
        this.sliderDotspanel = sliderDotspanel;
        dotscount = customAdapter.getCount();
        dots = new ImageView[dotscount];
        this.context = context;
    }

    private void initializeDots(){
        for(int i = 0;  i < dotscount; i++){

            dots[i] = new ImageView(context);
            dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pikjoaktive));
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pik));

    }

    public void setListener(){
        initializeDots();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i < dotscount; i++){

                    dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pikjoaktive));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pik));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
