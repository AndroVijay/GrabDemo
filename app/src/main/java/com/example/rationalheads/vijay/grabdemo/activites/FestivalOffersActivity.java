package com.example.rationalheads.vijay.grabdemo.activites;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.FestivalOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.model.Festival;

import java.util.ArrayList;


public class FestivalOffersActivity extends AppCompatActivity implements FestivalOfferAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private ArrayList<Festival> arrayList;
    private String title = "Festival Offers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_offers);
        arrayList = new ArrayList();

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);


        loadRecyclerview();


    }

    private void loadRecyclerview() {

        recyclerView = findViewById(R.id.festival_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new FestivalOfferAdapter(arrayList, this));
        FestivalOfferAdapter adapter=new FestivalOfferAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));



        arrayList.add(new Festival("11 Offers", "Mother's Day"));
        arrayList.add(new Festival("7 Offers", "Ramzan"));
        arrayList.add(new Festival("5 Offers", "Friendship Day"));
        arrayList.add(new Festival("14 Offers", "Raksha Bandhan"));
        arrayList.add(new Festival("15 Offers", "Women's Day"));
        arrayList.add(new Festival("13 Offers", "Dussehra"));
        arrayList.add(new Festival("28 Offers", "Diwali"));
        arrayList.add(new Festival("11 Offers", "Amazon Great India Sale"));
        arrayList.add(new Festival("10 Offers", "Republic Day"));
        arrayList.add(new Festival("22 Offers", "New Year"));
        arrayList.add(new Festival("32 Offers", "Valentines Day"));
        arrayList.add(new Festival("38 Offers", "Holi"));
        arrayList.add(new Festival("15 Offers", "Christmas"));
        arrayList.add(new Festival("10 Offers", "Ganesh Chaturthi"));
        arrayList.add(new Festival("9 Offers", "Pongal"));
        arrayList.add(new Festival("8 Offers", "Father's Day"));
        arrayList.add(new Festival("17 Offers", "Independence Day"));
        arrayList.add(new Festival("67 Offers", "Children's Day"));
        arrayList.add(new Festival("1 Offers", "Paytm Sale"));
        arrayList.add(new Festival("27 Offers", "Flash Sale"));
        arrayList.add(new Festival("5 Offers", "OMG Sale"));
        arrayList.add(new Festival("27 Offers", "EOSS"));
        arrayList.add(new Festival("5 Offers", "Flipkart Big Billion Day Sale"));


    }

    @Override
    public void onItemClick(int position) {


        Intent fest=new Intent(FestivalOffersActivity.this, FestivalOfferItemListnerActivity.class);

        Festival festival=arrayList.get(position);

        fest.putExtra("TITLE",festival.getFesttle());
        startActivity(fest);

    }
}
