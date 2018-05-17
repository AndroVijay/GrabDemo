package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.rationalheads.vijay.grabdemo.fragments.AllBlankOfferFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalAllFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalCouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.FestivalOfferFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.PopularBankOfferFragment;
import com.example.rationalheads.vijay.grabdemo.model.Banks;
import com.example.rationalheads.vijay.grabdemo.model.Festival;

import java.util.ArrayList;

public class BankOfferViewPagerAdapter extends FragmentViewPagerAdapter{




    int tabCount = 2;

    public BankOfferViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                PopularBankOfferFragment popular = new PopularBankOfferFragment();

                return popular;

            case 1:
                AllBlankOfferFragment all = new AllBlankOfferFragment();

                return all;

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
                return "POPULAR";
            case 1:
                return "ALL";


        }
        return null;
    }
}
