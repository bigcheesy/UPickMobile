package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by D'CyberPanda on 6/22/2017.
 */

public class DatabaseConnection {
    private static final String serverAddress = "http://45.55.135.14";
    public static final String BARPICS_DIRECTORY = "bar_pics";
    public static final String OFFERPICS_DIRECTORY = "bar_offers";

    public interface VolleyCallback{
        void onSuccess(Object result);
    }

    public static void getBarMenu(Context context, final VolleyCallback callback){
        String url = serverAddress + "/get_bars.php";
        final ArrayList<Bar> bars = new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,(String)null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("bars");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Bar bar = new Bar(object.getString("bar_name"), object.getString("bar_address"), 5, object.getString("pic_src"), object.getString("db_name"));
                        bars.add(bar);
                    }

                    callback.onSuccess(bars);
                } catch (JSONException x) {
                    System.out.print(x);
                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("lmao",error.getMessage());

            }
        });

        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    public static void getImage(Context context,String dirsrc, String pic_src, final VolleyCallback volleyCallback){
        String url = serverAddress + "/" + dirsrc + "/" + pic_src;

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                volleyCallback.onSuccess(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("lmao",error.getMessage());
            }
        });
        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(imageRequest);
    }

}
