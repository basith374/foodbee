package com.foodbee.foodbee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    String TAG = "RestaurantActivity";
    List<Restaurant> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        list.add(new Restaurant("KFC", "Cash, Credit Card", 456));
        list.add(new Restaurant("McDonalds", "Cash, Credit Card", 698));
        list.add(new Restaurant("Rotana Beach Club", "Cash, Credit Card", 349));
        list.add(new Restaurant("Thalassery Club", "Cash Credit", 583));

        ListView listView = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(this, R.layout.order_list_item);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    class Restaurant {
        Restaurant(String name, String payment, int review) {
            this.name = name;
            this.payment = payment;
            this.review = review;
        }
        String name;
        String payment;
        int review;
    }


    class MyAdapter extends ArrayAdapter<Restaurant> {
        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Restaurant getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Restaurant restaurant = getItem(i);
            View itemView = getLayoutInflater().inflate(R.layout.restaurant_list_item, viewGroup, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RestaurantActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            });
            ((TextView) itemView.findViewById(R.id.name)).setText(restaurant.name);
            ((TextView) itemView.findViewById(R.id.payment)).setText(restaurant.payment);
//            ((TextView) itemView.findViewById(R.id.review)).setText(menu.review);
            return itemView;
        }
    }
}
