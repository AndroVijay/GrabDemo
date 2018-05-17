package com.example.rationalheads.vijay.grabdemo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.App;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private View mView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         mView=inflater.inflate(R.layout.fragment_setting, container, false);

        return mView;
    }

}
