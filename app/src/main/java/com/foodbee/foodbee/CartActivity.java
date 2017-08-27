package com.foodbee.foodbee;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    List<CartRow> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        list.add(new CartHead("Rara Avis", "Thalassery"));
        list.add(new CartItem("Chicken Sandwich", 1, 29));
        list.add(new CartItem("Doner Kabab", 2, 384));
        list.add(new CartItem("Paratta", 3, 34));
        list.add(new CartItem("Manchurian Fries", 1, 48));
        list.add(new CartItem("Foozie Fries", 1, 48));
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this, R.layout.cart_list_item));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    class CartItem implements CartRow {
        CartItem(String name, int qty, double price) {
            this.name = name;
            this.qty = qty;
            this.price = price;
        }
        String name;
        int qty;
        double price;
    }

    class CartHead implements CartRow {
        CartHead(String name, String place) {
            this.name = name;
            this.place = place;
        }
        String name;
        String place;
    }

    interface CartRow {
    }

    private class MyAdapter extends ArrayAdapter<CartRow> {
        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CartRow getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CartRow row = getItem(position);
            View view = null;
            if(row instanceof CartItem) {
                CartItem item = (CartItem) row;
                view = getLayoutInflater().inflate(R.layout.cart_list_item, parent, false);
                ((TextView) view.findViewById(R.id.name)).setText(item.name);
                ((TextView) view.findViewById(R.id.price)).setText(String.valueOf(item.price));
                ((EditText) view.findViewById(R.id.qty)).setText(String.valueOf(item.qty));
                ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(item.qty * item.price));
            } else if(row instanceof CartHead) {
                CartHead item = (CartHead) row;
                view = getLayoutInflater().inflate(R.layout.cart_list_head, parent, false);
                ((TextView) view.findViewById(R.id.name)).setText(item.name);
                ((TextView) view.findViewById(R.id.place)).setText("(" + item.place + ")");
            }
            return view;
        }
    }
}
