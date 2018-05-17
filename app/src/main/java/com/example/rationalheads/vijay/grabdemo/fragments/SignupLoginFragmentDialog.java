package com.example.rationalheads.vijay.grabdemo.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.LoginActivity;
import com.example.rationalheads.vijay.grabdemo.adapters.SignupDialogViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupLoginFragmentDialog extends DialogFragment {

    private SignupDialogViewPagerAdapter signupDialogViewPagerAdapter;
    private ViewPager viewPager;
    private  View mView;
    private TabLayout tabLayout;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setLayout(700, 890);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         mView=inflater.inflate(R.layout.fragment_signup_login_fragment_dialog,container,false);


        // tab slider
        signupDialogViewPagerAdapter = new SignupDialogViewPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = mView.findViewById(R.id.pager);
        tabLayout=mView.findViewById(R.id.dialog_tabs);
        viewPager.setAdapter(signupDialogViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return  mView;
    }
}
