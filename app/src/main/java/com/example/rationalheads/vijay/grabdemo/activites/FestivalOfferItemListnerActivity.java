package com.example.rationalheads.vijay.grabdemo.activites;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.FestivalPager;

public class FestivalOfferItemListnerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private TabLayout  tabLayout;
    private ViewPager viewPager;
    private int betweenSpace = 100;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_offer_item_listner);

        Intent data=getIntent();
        title=data.getStringExtra("TITLE");

        toolbar=findViewById(R.id.festival_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.festival_collapse_toolbar);
        actionBar.setTitle(title);
        collapsingToolbar.setTitleEnabled(false);
//        collapsingToolbar.setTitle(title);


        tabLayout = findViewById(R.id.festival_tab);
        viewPager = findViewById(R.id.festival_viewpager);
        viewPager.setAdapter(new FestivalPager(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);



        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);

        for (int i=0; i<slidingTabStrip.getChildCount()-1; i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
        }
    }
}
