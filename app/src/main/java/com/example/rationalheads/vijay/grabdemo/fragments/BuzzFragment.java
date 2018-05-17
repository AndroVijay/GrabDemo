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
public class BuzzFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_buzz,container,false);

        return view ;
    }

}
