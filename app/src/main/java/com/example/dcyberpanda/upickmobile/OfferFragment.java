package com.example.dcyberpanda.upickmobile;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by D'CyberPanda on 6/6/2017.
 */

public class OfferFragment extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.offer_frag, container, false);
        ImageView adPanel = (ImageView) fragmentView.findViewById(R.id.adPanel);

        ArrayList<Integer> drawableIds = new ArrayList<>();
        drawableIds.add(R.drawable.placeholderad);
        drawableIds.add(R.drawable.placeholderad2);
        drawableIds.add(R.drawable.placeholderad3);

        int index = getArguments().getInt(MainActivity.OFFER_INDEX);

        adPanel.setImageResource(drawableIds.get(index));
        return fragmentView;
    }
}
