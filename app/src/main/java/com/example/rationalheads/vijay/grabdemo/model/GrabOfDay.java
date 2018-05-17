package com.example.rationalheads.vijay.grabdemo.model;

import com.example.rationalheads.vijay.grabdemo.R;

import java.util.Random;

/**
 * Created by vijay on 26-04-2018.
 */

public class GrabOfDay {

    private static final Random RANDOM = new Random();

    public static int getRandomCheeseDrawable() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.drawable.cardimage;
            case 1:
                return R.drawable.cardimage;
            case 2:
                return R.drawable.cardimage;
            case 3:
                return R.drawable.cardimage;
            case 4:
                return R.drawable.cardimage;
        }
    }
}
