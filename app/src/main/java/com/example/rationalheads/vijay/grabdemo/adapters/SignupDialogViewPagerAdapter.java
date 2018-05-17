package com.example.rationalheads.vijay.grabdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.rationalheads.vijay.grabdemo.fragments.LoginDialogFragment;
import com.example.rationalheads.vijay.grabdemo.fragments.SignupDeialogFragment;

/**
 * Created by vijay on 19-04-2018.
 */
public class SignupDialogViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount = 2;

    public SignupDialogViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            // find first fragment...
            SignupDeialogFragment ft1 = new SignupDeialogFragment();
            return ft1;
        }
        if (position == 1)
        {
            // find first fragment...
           LoginDialogFragment ft2 = new LoginDialogFragment();
            return ft2;
        }

        return null;
    }

//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//
//            case 0:
//
//                SignupDeialogFragment signupDeialogFragment=new SignupDeialogFragment();
//
//                return signupDeialogFragment;
//
//
//            case 1:
//                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
//
//                return loginDialogFragment;
//        }
//        return null;
//    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Sign Up";
            case 1:
                return "Login";

        }
        return null;
    }
}
