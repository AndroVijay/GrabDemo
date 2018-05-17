package com.example.rationalheads.vijay.grabdemo.activites;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.CouponFinderPagerAdapter;

public class CouponFinderActivity extends AppCompatActivity {

    private ViewFlipper simpleViewFlipper;
    private int[] images = {R.drawable.tab1, R.drawable.tab2, R.drawable.tab3, R.drawable.tab4};// array of images
    private String[] title={"Recharge","Food","Movies","Bus Booking"};
    private ActionBar actionBar;
    private Toolbar toolbar;
    private GestureDetector mGestureDetector;
    private ViewPager viewPager;
    private CouponFinderPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_finder);

        toolbar = findViewById(R.id.coupon_finder_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get The references of ViewFlipper
       // simpleViewFlipper = findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper


        // Add all the images to the ViewFlipper
//        for (int i = 0; i < images.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(images[i]);
//            simpleViewFlipper.addView(imageView);
//        }
//        // Set in/out flipping animations
//        simpleViewFlipper.setInAnimation(this, android.R.anim.fade_in);
//        simpleViewFlipper.setOutAnimation(this, android.R.anim.fade_out);
//
//        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
//        mGestureDetector = new GestureDetector(this, customGestureDetector);
//    }
//
//    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//
//            // Swipe left (next)
//            if (e1.getX() > e2.getX()) {
//                simpleViewFlipper.showNext();
//            }
//
//            // Swipe right (previous)
//            if (e1.getX() < e2.getX()) {
//                simpleViewFlipper.showPrevious();
//            }
//
//            return super.onFling(e1, e2, velocityX, velocityY);
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//
//        return super.onTouchEvent(event);
//    }


//        // loop for creating ImageView's
//        for (int i = 0; i < images.length; i++) {
//            // create the object of ImageView
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(images[i]); // set image in ImageView
//            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
//        }
//        // Declare in and out animations and load them using AnimationUtils class
//        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
//        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
//        // set the animation type's to ViewFlipper
//        simpleViewFlipper.setInAnimation(in);
//        simpleViewFlipper.setOutAnimation(out);
//        // set interval time for flipping between views
//        simpleViewFlipper.setFlipInterval(3000);
//        // set auto start for flipping between views
//        simpleViewFlipper.setAutoStart(true);
        viewPager = findViewById(R.id.viewPager);
        adapter = new CouponFinderPagerAdapter(CouponFinderActivity.this, images);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setPadding(40, 0, 320, 0);
        viewPager.setPageMargin(60);
        viewPager.setAdapter(adapter);


        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });
    }
}
