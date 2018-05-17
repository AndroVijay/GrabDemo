package com.example.rationalheads.vijay.grabdemo.activites;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.HelpSuportAdapter;
import com.example.rationalheads.vijay.grabdemo.fragments.ContactUs;
import com.example.rationalheads.vijay.grabdemo.fragments.PartnerWithUs;
import com.example.rationalheads.vijay.grabdemo.fragments.PrivacyPolicy;
import com.example.rationalheads.vijay.grabdemo.fragments.SettingFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.TermsofUse;

public class SettingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView what_app,face_book,twitter_img,share_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (App.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        toolbar=findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialized all imageview
        inti();

        SwitchCompat switchCompat = findViewById(R.id.switchCompat);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)

            switchCompat.setChecked(true);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        App.getInstance().setIsNightModeEnabled(true);
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        startActivity(intent);

                    } else {
                        App.getInstance().setIsNightModeEnabled(false);
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        startActivity(intent);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        });


//        // for daynight viewpager
//        viewPager =  findViewById(R.id.setting_pager);
//        addTabs(viewPager);
//        tabLayout =  findViewById(R.id.setting_tabs);
//        tabLayout.setupWithViewPager(viewPager);


    }

    private void inti() {


        what_app=findViewById(R.id.whatapp);
        face_book=findViewById(R.id.facebook);
        twitter_img=findViewById(R.id.twitter);
        share_img=findViewById(R.id.share_other);

        what_app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });

        twitter_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                try {
                    Intent i = new Intent();
                    i.putExtra(Intent.EXTRA_TEXT, "this is my app");
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://twitter.com/intent/tweet?text="));
                    startActivity(i);
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Twitter app isn't found", Toast.LENGTH_LONG).show();
                }

            }
        });

        share_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\n Let me recommend you this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/LoginRational\n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

//    private void addTabs(ViewPager viewPager) {
//
//
//            HelpSuportAdapter adapter = new HelpSuportAdapter(getSupportFragmentManager());
//            adapter.addFrag(new SettingFragment(), "Night Mode");
//            viewPager.setAdapter(adapter);
//
//    }


}
