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
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.foodbee.foodbee.MenuActivity;
import com.foodbee.foodbee.R;
import com.foodbee.foodbee.RestaurantActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Category> list;
    private List<Food> foods;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
        list = new ArrayList<>();
        list.add(new Category("Most Selling"));
        list.add(new Category("Appetizers"));
        list.add(new Category("Terwika"));
        list.add(new Category("Manouche Fern"));
        list.add(new Category("Wrap Saj"));
        list.add(new Category("Pizza"));
        foods = new ArrayList<>();
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
        foods.add(new Food("Veg Kuruma", "A hint of parsley", "Gourmet Made, Delicious", "129"));
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
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

    class Category {
        Category(String name) {
            this.name = name;
        }
        String name;
    }

    class Food {
        Food(String name, String desc, String extra, String price) {
            this.name = name;
            this.desc = desc;
            this.extra = extra;
            this.price = price;
        }
        String name;
        String desc;
        String extra;
        String price;
    }

    class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return foods.size();
        }

        @Override
        public Object getGroup(int i) {
            return list.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return foods.get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            Category cat = list.get(i);
            View itemView = getActivity().getLayoutInflater().inflate(R.layout.category_item, viewGroup, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(cat.name);

            if (b) {
                ((ImageView) itemView.findViewById(R.id.icon)).setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                ((ImageView) itemView.findViewById(R.id.icon)).setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
            return itemView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            Food food = foods.get(i);
            View itemView = getActivity().getLayoutInflater().inflate(R.layout.food_list_item, viewGroup, false);
            ((TextView) itemView.findViewById(R.id.name)).setText(food.name);
            ((TextView) itemView.findViewById(R.id.desc)).setText(food.desc);
            ((TextView) itemView.findViewById(R.id.extra)).setText(food.extra);
            ((TextView) itemView.findViewById(R.id.price)).setText(food.price);
            return itemView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

}
