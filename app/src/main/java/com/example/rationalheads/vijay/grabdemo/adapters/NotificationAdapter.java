package com.example.rationalheads.vijay.grabdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rationalheads.vijay.grabdemo.fragments.BuzzFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.CouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.DealsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.HotOfferFragment;

/**
 * Created by vijay on 12-04-2018.
 */

public class NotificationAdapter extends FragmentPagerAdapter {

    int tabCount = 2;

    public NotificationAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                BuzzFragment buzzFragment = new BuzzFragment();

                return buzzFragment;

            case 1:
                HotOfferFragment hotOfferFragment = new HotOfferFragment();

                return hotOfferFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "BUZZ";
            case 1:
                return "HOT OFFERS";

        }
        return null;
    }
}
