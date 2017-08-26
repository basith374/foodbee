package com.foodbee.foodbee.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.foodbee.foodbee.MenuActivity;
import com.foodbee.foodbee.R;
import com.foodbee.foodbee.RestaurantActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReviewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    List<Review> list;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewsFragment newInstance(String param1, String param2) {
        ReviewsFragment fragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list = new ArrayList<>();
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));
        list.add(new Review("A cool customer", "26 Aug 2017", "Awesome", 4.5f));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.review_list_item));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class Review {
        float rating;
        String name;
        String date;
        String review;

        public Review(String name, String date, String review, float rating) {
            this.name = name;
            this.date = date;
            this.review = review;
            this.rating = rating;
        }
    }

    class MyAdapter extends ArrayAdapter<Review> {

        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Review getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Review review = getItem(i);
            View itemView = getActivity().getLayoutInflater().inflate(R.layout.review_list_item, viewGroup, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(review.name);
            ((TextView) itemView.findViewById(R.id.date)).setText(review.date);
            ((TextView) itemView.findViewById(R.id.review)).setText(review.review);
            return itemView;
        }
    }
}
