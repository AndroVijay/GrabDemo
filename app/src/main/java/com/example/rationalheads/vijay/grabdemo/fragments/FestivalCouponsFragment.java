package com.example.rationalheads.vijay.grabdemo.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.rationalheads.vijay.grabdemo.adapters.AllOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FestivalCouponsFragment extends Fragment {

    public static String base_url = "https://api.github.com/users";
    ArrayList<Deals> list;
    RecyclerView recyclerView;


    public FestivalCouponsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_festival_coupons, container, false);

        list = new ArrayList();

        //parse json url
        getAllOfferFromUrl();

        recyclerView = view.findViewById(R.id.coupon_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void getAllOfferFromUrl() {

//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("");
//        progressDialog.show();
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
                                AllOfferAdapter adapter = new AllOfferAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
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

               // progressDialog.dismiss();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(req);

    }
    }


