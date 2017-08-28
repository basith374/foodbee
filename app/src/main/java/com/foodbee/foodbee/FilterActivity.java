package com.foodbee.foodbee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    List<FilterRow> filter_by;
    List<FilterRow> sort_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupSortByFilters();
        setupFilterByFilters();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupSortByFilters() {
        sort_by = new ArrayList<>();
        sort_by.add(new FilterRadio("Recommended", 1));
        sort_by.add(new FilterRadio("Ratings", 1));
        sort_by.add(new FilterRadio("Newest first", 1));
        sort_by.add(new FilterRadio("A to Z", 1));
        sort_by.add(new FilterRadio("Min. Order Amount", 1));
        sort_by.add(new FilterRadio("Fastest Delivery", 1));

        fillFilters(sort_by, (ViewGroup) findViewById(R.id.sort_by_list));
    }

    private void setupFilterByFilters() {
        filter_by = new ArrayList<>();
        filter_by.add(new FilterSwitch("Offers"));
        filter_by.add(new FilterSwitch("New Restaurants"));
        filter_by.add(new FilterSwitch("Pre Order"));

        fillFilters(filter_by, (ViewGroup) findViewById(R.id.filter_by_list));
    }

    private void fillFilters(List<FilterRow> filters, ViewGroup parent) {
        for(FilterRow row: filters) {
            View itemView = null;
            if(row instanceof FilterRadio) {
                FilterRadio radio = (FilterRadio) row;
                itemView = getLayoutInflater().inflate(R.layout.filter_radio_item, parent, false);
                ((TextView) itemView.findViewById(R.id.text)).setText(radio.name);
            } else if(row instanceof FilterSwitch) {
                FilterSwitch s_witch = (FilterSwitch) row;
                itemView = getLayoutInflater().inflate(R.layout.filter_switch_item, parent, false);
                ((TextView) itemView.findViewById(R.id.text)).setText(s_witch.name);
            }
            parent.addView(itemView);
        }
    }

    interface FilterRow {

    }

    class FilterSwitch implements FilterRow {
        FilterSwitch(String name) {
            this.name = name;
        }
        String name;
    }

    class FilterRadio implements FilterRow {
        FilterRadio(String name, int id) {
            this.name = name;
            this.id = id;
        }
        String name;
        int id;
    }

}
