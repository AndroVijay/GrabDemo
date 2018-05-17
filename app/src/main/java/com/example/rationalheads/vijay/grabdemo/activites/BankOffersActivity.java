package com.example.rationalheads.vijay.grabdemo.activites;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.BankOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.BankOfferViewPagerAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.FestivalOfferAdapter;
import com.example.rationalheads.vijay.grabdemo.adapters.FestivalPager;
import com.example.rationalheads.vijay.grabdemo.model.Banks;
import com.example.rationalheads.vijay.grabdemo.model.Festival;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class BankOffersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int betweenSpace = 50;
    private String title = "Bank Offers";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_offers);


        toolbar = findViewById(R.id.bank_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.bank_collapse_toolbar);
        actionBar.setTitle(title);
        collapsingToolbar.setTitleEnabled(false);


        tabLayout = findViewById(R.id.bank_tab);
        viewPager = findViewById(R.id.bank_viewpager);
        viewPager.setAdapter(new BankOfferViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);

        for (int i = 0; i < slidingTabStrip.getChildCount() - 1; i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_search_menu, menu);
//        // Retrieve the SearchView and plug it into SearchManager
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        return true;

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;

    }

}
