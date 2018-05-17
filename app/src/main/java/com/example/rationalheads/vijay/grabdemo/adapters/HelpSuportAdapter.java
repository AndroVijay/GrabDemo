package com.example.rationalheads.vijay.grabdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.rationalheads.vijay.grabdemo.fragments.ContactUs;
import com.example.rationalheads.vijay.grabdemo.fragments.CouponsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.DealsFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.PartnerWithUs;
import com.example.rationalheads.vijay.grabdemo.fragments.PrivacyPolicy;
import com.example.rationalheads.vijay.grabdemo.fragments.TermsofUse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay on 24-04-2018.
 */

public class HelpSuportAdapter extends FragmentViewPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public HelpSuportAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
