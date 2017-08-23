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

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    private List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new Order("Paneer Tikka", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Family Rice", "Frozen Shakalaka, Zinger Burger"));
        list.add(new Order("Ghee Kabab", "Fried Legs, Prawn Shells"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));

        ListView listView = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(this, R.layout.order_list_item);
        listView.setAdapter(adapter);
    }

    public void openHotels(View view) {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
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

    class MyAdapter extends ArrayAdapter<Order> {
        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Order getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Order order = getItem(i);
            View itemView = getLayoutInflater().inflate(R.layout.order_list_item, viewGroup, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(order.name);
            ((TextView) itemView.findViewById(R.id.desc)).setText(order.desc);
            return itemView;
        }
    }
}
