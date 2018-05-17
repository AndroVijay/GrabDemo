package com.example.rationalheads.vijay.grabdemo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.ApiDemoCustomAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.CouponsRecyclerviewAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.ImageFlipperAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.RecyclerViewDataAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Deals;
import com.example.rationalheads.vijay.grabdemo.model.SectionDataModel;
import com.example.rationalheads.vijay.grabdemo.model.SingleItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.rationalheads.vijay.grabdemo.fragments.DealsFragment.base_url;

/**
 * Created by vijay on 12-04-2018.
 */

public class CouponsFragment extends Fragment {


    private Runnable runnable;
    private Handler handler;
    private long delay = 3000; //milliseconds
    private long period=3000;
    private View view;
    private Timer swipeTimer;
    ArrayList<SectionDataModel> allSampleData;
    RecyclerView my_recycler_view, coupon_recyclerview;
    RecyclerViewDataAdapter adapter;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.tab1, R.drawable.tab2, R.drawable.tab3, R.drawable.tab4, R.drawable.tab5, R.drawable.tab6, R.drawable.tab7};
    private ArrayList<Integer> ImagesArray = new ArrayList();
    private ImageFlipperAdapter imageFlipperAdapter;
    ArrayList<Deals> list=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.coupons_fragment, container, false);


        allSampleData = new ArrayList<>();
        handler=new Handler();

        //method for dummy data
        createDummyData();

        my_recycler_view = view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        adapter = new RecyclerViewDataAdapter(getContext(), allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);


        // initialized flipper
        initializeflipper();


        // get data from server
        //getDataFromUrl();
        //coupon_recyclerview = view.findViewById(R.id.vertical_recyclerview);
       // coupon_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void getDataFromUrl() {

        JsonArrayRequest req = new JsonArrayRequest(base_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            // Parsing json array response
                            // loop through each json object

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);

                                String name = person.getString("login");
                                String image = person.getString("avatar_url");

                                Deals deals = new Deals(name, image);

                                list.add(deals);
                                CouponsRecyclerviewAdapter adapter = new CouponsRecyclerviewAdapter(getContext(), list);
                                coupon_recyclerview.setAdapter(adapter);
                                //progressDialog.dismiss();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(req);
    }

    private void createDummyData() {

        for (int i = 1; i <= 10; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Best Offers ");

            ArrayList<SingleItemModel> singleItem = new ArrayList<>();
            for (int j = 0; j <= 15; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }


    }


    private void initializeflipper() {

        for (int i = 0; i < IMAGES.length; i++)

            ImagesArray.add(IMAGES[i]);

        mPager = view.findViewById(R.id.pager);
        imageFlipperAdapter=new ImageFlipperAdapter(ImagesArray,getContext());
        mPager.setAdapter(imageFlipperAdapter);

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager

         runnable = new Runnable() {
            public void run() {
                if (imageFlipperAdapter.getCount() == NUM_PAGES) {
                    NUM_PAGES = 0;
                } else {
                    NUM_PAGES++;
                }
                mPager.setCurrentItem(NUM_PAGES, true);
                handler.postDelayed(this, delay);
            }
        };

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                NUM_PAGES = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

}
