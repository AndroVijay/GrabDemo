package com.example.rationalheads.vijay.grabdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rationalheads.vijay.grabdemo.fragments.CouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.DealsFragment;

/**
 * Created by vijay on 11-04-2018.
 */

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount = 2;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                CouponsFragment couponsFragment = new CouponsFragment();

                return couponsFragment;

            case 1:
                DealsFragment dealsFragment = new DealsFragment();

                return dealsFragment;
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
                return "COUPONS";
            case 1:
                return "DEALS";

        }
        return null;
    }
}
