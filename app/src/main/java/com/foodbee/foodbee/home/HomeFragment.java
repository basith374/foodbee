package com.foodbee.foodbee.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foodbee.foodbee.MainActivity;
import com.foodbee.foodbee.R;
import com.foodbee.foodbee.RestaurantActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private List<Order> list;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();
        list.add(new Order("Paneer Tikka", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Family Rice", "Frozen Shakalaka, Zinger Burger"));
        list.add(new Order("Ghee Kabab", "Fried Legs, Prawn Shells"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));
        list.add(new Order("Ratatouille", "Chicken Noodles, Veg Kuruma"));

        populateOrderList(view);

        ((EditText) view.findViewById(R.id.searchInput)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((ImageView) view.findViewById(R.id.goBtn))
                        .setImageResource(charSequence.length() == 0 ?
                                R.drawable.ic_my_location_white_24dp :
                                R.drawable.ic_search_white_24dp
                        );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateOrderList(View view) {
        LinearLayout listView = (LinearLayout) view.findViewById(R.id.list_view);
        for(Order o : list) {
            View itemView = getActivity().getLayoutInflater().inflate(R.layout.order_list_item, listView, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(o.name);
            ((TextView) itemView.findViewById(R.id.desc)).setText(o.desc);
            listView.addView(itemView);
        }
    }

    class Order {
        Order(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        String name;
        String desc;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
