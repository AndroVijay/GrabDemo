package com.example.rationalheads.vijay.grabdemo.activites;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.NotificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    int betweenSpace = 150;
    private ActionBar actionBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar=findViewById(R.id.notification_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        tabLayout = findViewById(R.id._notification_tabs);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new NotificationAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);

        for (int i=0; i<slidingTabStrip.getChildCount()-1; i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
        }
    }
}
