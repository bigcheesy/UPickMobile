package com.example.dcyberpanda.upickmobile.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcyberpanda.upickmobile.Bar;
import com.example.dcyberpanda.upickmobile.DatabaseConnection;
import com.example.dcyberpanda.upickmobile.ImageCache;
import com.example.dcyberpanda.upickmobile.MainActivity;
import com.example.dcyberpanda.upickmobile.R;

import java.util.ArrayList;


public class BarpickAdapter extends BaseAdapter implements Filterable{

    public static final String DBNAME_INDEX = "dbname_index";
    public static int viewid;

    private Context context;
    private ArrayList<Bar> originalItems;
    private ArrayList<Bar> displayedItems;
    private LayoutInflater inflater;

    public BarpickAdapter(Context context, ArrayList<Bar> mBarArrayList) {
        this.originalItems = mBarArrayList;
        this.displayedItems = mBarArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    static class DataHandler{
        TextView name;
        TextView address;
        RatingBar rating;
        ImageView imageView;
        CardView cardView;
    }

    @Override
    public int getCount(){
        return this.displayedItems.size();
    }

    @Override
    public Object getItem(int position){
        return this.displayedItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        viewid = R.layout.bar_item;

        View row;
        row = convertView;
        final DataHandler handler;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(viewid,parent,false);
            handler = new DataHandler();
            handler.name = (TextView) row.findViewById(R.id.bar_name);
            handler.address = (TextView) row.findViewById(R.id.bar_address);
            handler.cardView = (CardView) row.findViewById(R.id.bar_cardview);
            handler.imageView = (ImageView) row.findViewById(R.id.bar_image);
            handler.rating = (RatingBar) row.findViewById(R.id.bar_rating);


            row.setTag(handler);
        }else{
            handler = (DataHandler) row.getTag();
        }

        final Bar bar;
        bar = (Bar) this.getItem(position);

        final ImageCache imageCache = new ImageCache(context);
        final String imagesrc = bar.getDrawableid();
        if (imageCache.imageExists(imagesrc)){
            handler.imageView.setImageBitmap(imageCache.getFromCache(imagesrc));
        }else {
            DatabaseConnection.getImage(context, DatabaseConnection.BARPICS_DIRECTORY, bar.getDrawableid(), new DatabaseConnection.VolleyCallback() {
                @Override
                public void onSuccess(Object result) {
                    Bitmap resultBitmap = (Bitmap) result;
                    imageCache.writeInCache(resultBitmap, imagesrc);
                    handler.imageView.setImageBitmap(resultBitmap);
                }
            });
        }

        handler.name.setText(bar.getName());
        handler.address.setText(bar.getAddress());
        handler.rating.setRating(bar.getRating());

        handler.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(DBNAME_INDEX, bar.getDbanme());
                context.startActivity(intent);
            }
        });
        return row;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                displayedItems = (ArrayList<Bar>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Bar> FilteredArrList = new ArrayList<>();

                if (originalItems == null) {
                    originalItems = new ArrayList<>(displayedItems); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = originalItems.size();
                    results.values = originalItems;
                } else {
                    constraint = constraint.toString().toLowerCase();

                    boolean hasResults = false;

                    for (Bar bar : originalItems){
                        String data = bar.getName();
                        if (data.toLowerCase().contains(constraint.toString())){
                            FilteredArrList.add(bar);
                            hasResults = true;
                        }
                    }

                    if (!hasResults){
                        results.count = originalItems.size();
                        results.values = originalItems;
                        Toast.makeText(context,"Nuk ka rezultate nga kerkimi juaj!",Toast.LENGTH_SHORT).show();
                    }else {
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                }
                return results;
            }
        };
        return filter;
    }
}