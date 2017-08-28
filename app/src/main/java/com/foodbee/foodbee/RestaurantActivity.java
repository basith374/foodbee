package com.foodbee.foodbee;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    String TAG = "RestaurantActivity";
    List<RestaurantRow> list;
    SearchView searchView;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        list.add(new RestaurantHead("RESTAURANTS"));
        list.add(new Restaurant("KFC", "Cash, Credit Card", 456));
        list.add(new Restaurant("McDonalds", "Cash, Credit Card", 698));
        list.add(new Restaurant("Rotana Beach Club", "Cash, Credit Card", 349));
        list.add(new Restaurant("Thalassery Club", "Cash Credit", 583));

        ListView listView = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(this, R.layout.order_list_item);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.restaurant, menu);
        setUpSearchView(menu);
        return true;
    }

    private void setUpSearchView(Menu menu) {
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.filter_btns).setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                findViewById(R.id.filter_btns).setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showFilters(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }

    public void showCuisines(View view) {
        Intent intent = new Intent(this, CuisineActivity.class);
        startActivity(intent);
    }

    interface RestaurantRow {

    }

    class RestaurantHead implements RestaurantRow {
        RestaurantHead(String name) {
            this.name = name;
        }
        String name;
    }

    class Restaurant implements RestaurantRow {
        Restaurant(String name, String payment, int review) {
            this.name = name;
            this.payment = payment;
            this.review = review;
        }
        String name;
        String payment;
        int review;
    }


    class MyAdapter extends ArrayAdapter<RestaurantRow> {
        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public RestaurantRow getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            RestaurantRow row = getItem(i);
            View itemView = null;
            if(row instanceof Restaurant) {
                Restaurant restaurant = (Restaurant) row;
                itemView = getLayoutInflater().inflate(R.layout.restaurant_list_item, viewGroup, false);
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
            } else if(row instanceof RestaurantHead) {
                RestaurantHead head = (RestaurantHead) row;
                itemView = getLayoutInflater().inflate(R.layout.restaurant_list_head, viewGroup, false);
                ((TextView) itemView.findViewById(R.id.text)).setText(head.name);
            }
            return itemView;
        }
    }
}
