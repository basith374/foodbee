package com.foodbee.foodbee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CuisineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.list_view);
        String[] items = {
                "All Cuisines",
                "American",
                "Arabic",
                "Asian",
                "Beverages",
                "Cafe",
                "Chinese",
                "Desserts",
                "Egyptian",
                "Grocery",
                "Healthy Food",
                "Broccoli",
                "Foodbee Special"
        };
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
