package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static void getImage(Context context,String pic_dir, String pic_src, final VolleyCallback volleyCallback){
        String url;

        if (!pic_dir.equals(OFFERPICS_DIRECTORY)) {
            url = serverAddress + "/" + pic_dir + "/" + pic_src;
        }else{
            url = serverAddress + "/" + pic_dir + "/" + removeDbPrefix(MainActivity.CURRENT_BAR) + "/" + pic_src;
        }

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

    public static void getRatings  (Context context, final VolleyCallback callback)
    {
        String url = serverAddress + "/get_ratings.php";
        final ArrayList<Rating> ratings = new ArrayList<>();
        Map<String,String> params = new HashMap<>();
        params.put("name",MainActivity.CURRENT_BAR);

        final CustomJsonRequest jsonObjectRequest = new CustomJsonRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("ratings");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Rating rating = new Rating(object.getInt("user_id"), object.getString("user_name"), object.getString("user_comment"),object.getInt("user_rating"));
                        ratings.add(rating);
                    }

                    callback.onSuccess(ratings);
                }catch (JSONException y){
                    Log.d("lmao", y.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("lmao",error.getMessage());
            }
        });
        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    public static void getMenu(Context context, final VolleyCallback callback){
        String url = serverAddress + "/get_menu.php";
        final ArrayList<MenuItem> items = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("name",MainActivity.CURRENT_BAR);

        final CustomJsonRequest jsonObjectRequest = new CustomJsonRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("menu");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        MenuItem item = new MenuItem(object.getString("item_name"),object.getInt("item_price"),object.getString("item_category"));
                        items.add(item);
                    }

                    callback.onSuccess(items);
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

    public static void getOffers(Context context, final VolleyCallback callback)
    {
        String url = serverAddress + "/get_offers.php";
        final ArrayList<String> offers = new ArrayList<>();
        Map<String,String> params = new HashMap<>();
        params.put("name",MainActivity.CURRENT_BAR);

        final CustomJsonRequest jsonObjectRequest = new CustomJsonRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("offers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String offer = object.getString("pic_src");
                        offers.add(offer);
                    }
                    callback.onSuccess(offers);
                }catch (JSONException y){
                    Log.d("lmao", y.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("lmao",error.getMessage());
            }
        });
        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    public static void createUser(final Context context, final String name, final String surname, final String phonenr, final String password, final VolleyCallback callback){
        String url = serverAddress + "/set_user.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Connection error! Try again.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_name",name);
                params.put("user_surname",surname);
                params.put("user_phonenr",phonenr);
                params.put("user_password",password);
                return params;
            }
        };
        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public static void login(final Context context, final String phonenr, final String password, final VolleyCallback callback){
        String url = serverAddress + "/login.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Object object = new JSONTokener(response).nextValue();
                    if (object instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) object;
                        User user = new User(jsonObject.getInt("user_id"),jsonObject.getString("user_name"),jsonObject.getString("user_surname"),jsonObject.getInt("user_points"));
                        Log.d("lmao","if");
                        callback.onSuccess(user);
                    }else{
                        Log.d("lmao","else");
                        callback.onSuccess(response);
                    }
                }catch (JSONException x){
                    Log.d("lmao",x.getMessage());
                    x.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Connection error! Try again.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_phonenr",phonenr);
                params.put("user_password",password);
                return params;
            }
        };
        ConnectionSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private static String removeDbPrefix(String str){
        return str.replace("bardb_","");
    }
}
