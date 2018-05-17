package com.example.rationalheads.vijay.grabdemo.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.BankOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Banks;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularBankOfferFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Banks> arrayList;
    private View view;
    private BankOfferAdapter adapter;


    public PopularBankOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        arrayList = new ArrayList<>();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bank_popular, container, false);

        loadRecyclerview1();

        return view;
    }

    private void loadRecyclerview1() {

        recyclerView = view.findViewById(R.id.popular_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//          recyclerView.setAdapter(new FestivalOfferAdapter(arrayList, this));

        // adapter.setOnItemClickListener(this);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        arrayList.add(new Banks("Yes Bank", "31 Offers"));
        arrayList.add(new Banks("SBI", "71 Offers"));
        arrayList.add(new Banks("ICICI", "111 Offers"));
        arrayList.add(new Banks("HDFC", "113 Offers"));
        arrayList.add(new Banks("IDBI", "4 Offers"));
        arrayList.add(new Banks("Axis", "96 Offers"));
        arrayList.add(new Banks("ING Vysya", "2 Offers"));
        arrayList.add(new Banks("Induslnd", "31 Offers"));
        arrayList.add(new Banks("Kotak", "50 Offers"));
        arrayList.add(new Banks("Citi", "22 Offers"));

        adapter = new BankOfferAdapter(getContext(),arrayList);


        recyclerView.setAdapter(adapter);
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
//
//        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        MenuItem search = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//        return true;
//    }

}


