package com.example.rationalheads.vijay.grabdemo.activites;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.HelpSuportAdapter;
import com.example.rationalheads.vijay.grabdemo.fragments.ContactUs;
import com.example.rationalheads.vijay.grabdemo.fragments.PartnerWithUs;
import com.example.rationalheads.vijay.grabdemo.fragments.PrivacyPolicy;
import com.example.rationalheads.vijay.grabdemo.fragments.TermsofUse;

public class HelpSuportActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    int betweenSpace =50;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private ImageView what_app,face_book,twitter_img,share_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_suport);

        toolbar=findViewById(R.id.help_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager =  findViewById(R.id.help_pager);
        addTabs(viewPager);

        tabLayout =  findViewById(R.id.help_tabs);
        tabLayout.setupWithViewPager(viewPager);



//        viewPager.setAdapter(new HelpSuportAdapter(getSupportFragmentManager()));
//        tabLayout.setupWithViewPager(viewPager);

        // initialized all imageview
        inti();






        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);

        for (int i=0; i<slidingTabStrip.getChildCount()-1; i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
        }


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

    private void addTabs(ViewPager viewPager) {

        HelpSuportAdapter adapter = new HelpSuportAdapter(getSupportFragmentManager());
        adapter.addFrag(new ContactUs(), "Contact Us");
        adapter.addFrag(new TermsofUse(), "Terms of Use");
        adapter.addFrag(new PrivacyPolicy(), "Privacy Policy");
        adapter.addFrag(new PartnerWithUs(), "Partner With Us");
        viewPager.setAdapter(adapter);
    }
}
