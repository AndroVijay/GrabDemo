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
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.MainActivity;
import com.example.rationalheads.vijay.grabdemo.databasehelpers.DatabaseHelper;

/**
 * Created by vijay on 20-04-2018.
 */

public class AlertDialogForLogin  extends DialogFragment{

    private View mView;
    DatabaseHelper helper;
    private EditText lEmail,lPass;
    private Button lLogin;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(mView);
        AlertDialog dialog = builder.create();
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

        helper = new DatabaseHelper(getContext());

        mView=inflater.inflate(R.layout.fragment_login_dialog,container,false);

        //initialization of all field
        intiloginfield();


        return mView;
    }

    private void intiloginfield() {

        lEmail=mView.findViewById(R.id.lemail);
        lPass=mView.findViewById(R.id.lpass);
        lLogin=mView.findViewById(R.id.input_login);

        lLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email=lEmail.getText().toString();
                String password = lPass.getText().toString();
                String pass=helper.searchUser(email);

                if (lEmail.getText().toString().length()<0 || lPass.getText().toString().length()<0){

                    Toast.makeText(getContext(), "please fill all record", Toast.LENGTH_SHORT).show();

                } else if (password.equals(pass))
                {
                    Intent intent=new Intent(getContext(),MainActivity.class);

                    String name=helper.getUserRecord(email);

                    intent.putExtra("Name",name);
                    startActivity(intent);

                }else
                {
                    Toast.makeText(getContext(), "username and password not match", Toast.LENGTH_SHORT).show();
                }

//                if (!isValidEmail(Email)) {
//                    lEmail.setError("Invalid Email");
//                }
//
//
//                if (!isValidPassword(pass)) {
//                    lPass.setError("Invalid Password");
//                }


            }
        });

    }
}
