package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by D'CyberPanda on 6/22/2017.
 */

public class ImageCache {

    private Context context;
    private File directory;

    public ImageCache(Context context){
        this.context = context.getApplicationContext();
        directory = getDir();
    }

    private File getDir(){
        File cacheDir = new File(context.getCacheDir(),"bar_pics");

        if (!cacheDir.exists()){
            if (!cacheDir.mkdir()){
                Log.d("lmao", "Error creating directory");
            }
        }
        return cacheDir;
    }

    public boolean imageExists(String imageSrc){
        File file = new File(directory,imageSrc);
        return file.exists();
    }

    public void writeInCache(Bitmap bitmap, String filename){
        FileOutputStream out = null;
        try {
            File file = new File(directory,filename);
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
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

    public Bitmap getFromCache(String filename){
        File path = new File(directory,filename);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(path.getAbsolutePath(), options);
    }
}
