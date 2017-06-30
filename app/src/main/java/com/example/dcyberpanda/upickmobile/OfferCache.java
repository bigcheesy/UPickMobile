package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by D'CyberPanda on 6/29/2017.
 */

public class OfferCache {

    public static ArrayList<String> offerSrcs;
    private Context context;
    private File directory;

    public OfferCache (Context context){
        this.context = context.getApplicationContext();
        directory = getDir();
        directory.deleteOnExit();
    }

    private File getDir(){
        File cacheDir = new File(context.getCacheDir(),"bar_offers");

        if (!cacheDir.exists()){
            if (!cacheDir.mkdir()){
                Log.d("lmao", "Error creating directory");
            }
        }
        return cacheDir;
    }

    public void writeInCache(Bitmap bitmap, String filename){
        FileOutputStream out = null;
        try {
            File file = new File(directory,filename);
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean imageExists(String imageSrc){
        File file = new File(directory,imageSrc);
        return file.exists();
    }

    public Bitmap getFromCache(String filename){
        File path = new File(directory,filename);
        return BitmapFactory.decodeFile(path.getAbsolutePath());
    }
}
