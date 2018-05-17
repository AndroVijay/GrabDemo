package com.example.rationalheads.vijay.grabdemo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.MainActivity;
import com.example.rationalheads.vijay.grabdemo.adapters.ApiDemoCustomAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vijay on 12-04-2018.
 */

public class DealsFragment extends Fragment implements PopupMenu.OnMenuItemClickListener{

    public static String base_url = "https://api.github.com/users";
    ArrayList<Deals> list;
    RecyclerView recyclerView;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.deals_fragment, container, false);

        list = new ArrayList();

        //parse json url
        getDataFromUrl();

        recyclerView = view.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // set floating action button

        getFloatingButton(view);



        return view;
    }



    private void getFloatingButton(View view) {

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(v);

                //buildDialog(R.style.DialogAnimation, "Animated Dialog");
            }
        });
    }

    private void popup(View v) {


        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.context_menu);
        popup.setGravity(Gravity.CENTER_HORIZONTAL);
        popup.show();
    }


//    private void buildDialog(int animationSource, String type) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//        builder.setMessage(type);
//        builder.setNegativeButton("Ok",null);
//        AlertDialog dialog = builder.create();
//        dialog.getWindow().getAttributes().windowAnimations = animationSource;
//        dialog.show();
//    }


    private void getDataFromUrl() {

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
                                ApiDemoCustomAdapter adapter = new ApiDemoCustomAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                                //progressDialog.dismiss();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                           // Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(req);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        Toast.makeText(getContext(), "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.myitem1:
                // do your code
                return true;
            case R.id.myitem2:
                // do your code
                return true;
            default:
                return false;
        }
    }
}

