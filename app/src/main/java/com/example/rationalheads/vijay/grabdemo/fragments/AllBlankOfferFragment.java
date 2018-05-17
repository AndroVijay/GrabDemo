package com.example.rationalheads.vijay.grabdemo.fragments;


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

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.BankOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Banks;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBlankOfferFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Banks> arrayList;
    private View view;
    private BankOfferAdapter adapter;

    public AllBlankOfferFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        arrayList=new ArrayList<>();
        // Inflate the layout for this fragment

         view=inflater.inflate(R.layout.fragment_all_blank_offer, container, false);



        loadRecyclerview2();


        return view;
    }

    private void loadRecyclerview2() {


        recyclerView = view.findViewById(R.id.all_recyclerview);
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
        arrayList.add(new Banks("Union Bank Of India","100 Offers"));
        arrayList.add(new Banks("Bank Of Baroda","50 Offers"));
        arrayList.add(new Banks("DBS","13 Offers"));
        arrayList.add(new Banks("Deutsche Bank", "10 Offers"));
        arrayList.add(new Banks("Dhanlaxmi Bank","2 Offers"));
        arrayList.add(new Banks("Jio Money","31 Offers"));
        arrayList.add(new Banks("Karnataka Bank","2 Offers"));


        adapter = new BankOfferAdapter(getContext(),arrayList);


        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

       // getActivity().getMenuInflater().inflate(R.menu.bank_search_menu, menu);
//
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
