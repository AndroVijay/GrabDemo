package com.example.rationalheads.vijay.grabdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.rationalheads.vijay.grabdemo.fragments.CouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.DealsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalAllFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalCouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalOfferFragment;

public class FestivalPager extends FragmentViewPagerAdapter {

    int tabCount = 3;

    public FestivalPager(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                FestivalAllFragment all = new FestivalAllFragment();

                return all;

            case 1:
                FestivalCouponsFragment coupons = new FestivalCouponsFragment();

                return coupons;

            case 2:

                FestivalOfferFragment offers = new FestivalOfferFragment();

                return offers;
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
                return "ALL";
            case 1:
                return "COUPONS";

            case 2:

                return "OFFERS";

        }
        return null;
    }
}
