package com.example.rationalheads.vijay.grabdemo.activites;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.rationalheads.vijay.grabdemo.R;

public class AppRatingActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView rateMessage;
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rating);

        toolbar=findViewById(R.id.rating_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // Initialize RatingBar
        ratingBar =  findViewById(R.id.ratingBar);
        rateMessage=findViewById(R.id.ratingMessage);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                String ratedValue = String.valueOf(ratingBar.getRating());

                rateMessage.setText("Rating : " + ratedValue + "/5");
            }
        });
    }

}
