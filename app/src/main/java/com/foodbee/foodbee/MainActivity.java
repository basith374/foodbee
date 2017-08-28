package com.foodbee.foodbee;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "MainActivity";

    private List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = new ArrayList<>();
        list.add(new Order("Paneer Tikka", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Family Rice", "Frozen Shakalaka, Zinger Burger"));
        list.add(new Order("Ghee Kabab", "Fried Legs, Prawn Shells"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));

        populateOrderList();

        ((EditText) findViewById(R.id.searchInput)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((ImageView) findViewById(R.id.goBtn))
                        .setImageResource(charSequence.length() == 0 ?
                                R.drawable.ic_my_location_white_24dp :
                                R.drawable.ic_search_white_24dp
                        );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    private void populateOrderList() {
        LinearLayout listView = (LinearLayout) findViewById(R.id.list_view);
        for(Order o : list) {
            View itemView = getLayoutInflater().inflate(R.layout.order_list_item, listView, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(o.name);
            ((TextView) itemView.findViewById(R.id.desc)).setText(o.desc);
            listView.addView(itemView);
        }
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void openHotels(View view) {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
//        Intent intent = new Intent(MainActivity.this, FoodActivity.class);
        startActivity(intent);
    }

    class Order {
        Order(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        String name;
        String desc;
    }

}
