package com.example.rationalheads.vijay.grabdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rationalheads.vijay.grabdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyPolicy extends Fragment {


    public PrivacyPolicy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy, container, false);
    }

}
