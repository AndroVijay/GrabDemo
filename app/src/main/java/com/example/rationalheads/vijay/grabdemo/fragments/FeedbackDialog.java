package com.example.rationalheads.vijay.grabdemo.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.FeedbackActivity;

/**
 * Created by vijay on 25-04-2018.
 */

public class FeedbackDialog  extends DialogFragment {

    private View mView;
    private TextView goback;
    private Button submit;
    private EditText Ftext,Femail;
    private  AlertDialog dialog;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(mView);
        dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setLayout(700, 770);
        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView=inflater.inflate(R.layout.feedback_alert_dialog,container,false);

        //initialization of all field
        intiloginfield();


        return mView;
    }

    private void intiloginfield() {


        Ftext=mView.findViewById(R.id.input_Text);
        Femail=mView.findViewById(R.id.input_Email);
        goback=mView.findViewById(R.id.go_back);
        submit=mView.findViewById(R.id.feed_submit);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });


    }
}
