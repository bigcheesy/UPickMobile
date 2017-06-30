package com.example.dcyberpanda.upickmobile;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
        final ImageView adPanel = (ImageView) fragmentView.findViewById(R.id.adPanel);

        int index = getArguments().getInt(MainActivity.OFFER_INDEX);

        final OfferCache offerCache = new OfferCache(getContext());
        final String imagesrc = OfferCache.offerSrcs.get(index);

        if (offerCache.imageExists(imagesrc)){
            adPanel.setImageBitmap(offerCache.getFromCache(imagesrc));
            Log.d("lmao","getting from cache");
        }else {
            DatabaseConnection.getImage(getContext(),DatabaseConnection.OFFERPICS_DIRECTORY ,OfferCache.offerSrcs.get(index), new DatabaseConnection.VolleyCallback() {
                @Override
                public void onSuccess(Object result) {
                    Log.d("lmao", "getting from webz");
                    Bitmap resultBitmap = (Bitmap) result;
                    offerCache.writeInCache(resultBitmap, imagesrc);
                    adPanel.setImageBitmap(resultBitmap);
                }
            });
        }

        return fragmentView;
    }
}
