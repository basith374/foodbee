package com.foodbee.foodbee;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
