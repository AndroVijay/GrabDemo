package com.example.rationalheads.vijay.grabdemo.fragments;


import android.database.Cursor;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.databasehelpers.DatabaseHelper;
import com.example.rationalheads.vijay.grabdemo.model.Contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupDeialogFragment extends Fragment {

    private EditText dEmail, dPassword, dName, dNumber;
    private Button dSignup;
    private View dview;
    private String MobilePattern = "[0-9]{10}";
    private DatabaseHelper helper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        helper = new DatabaseHelper(getContext());
        // Inflate the layout for this fragment
        dview = inflater.inflate(R.layout.fragment_signup_deialog, container, false);

        //initializedtextfield
        intifield();

        return dview;
    }

    private void intifield() {

        dEmail = dview.findViewById(R.id.input_email);
        dPassword = dview.findViewById(R.id.input_password);
        dName = dview.findViewById(R.id.input_Name);
        dNumber = dview.findViewById(R.id.input_phone);

        dSignup = dview.findViewById(R.id.input_signup);
        dSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                 String email = dEmail.getText().toString();
                 String pass = dPassword.getText().toString();
                 String name = dName.getText().toString();
                 String mobile=dNumber.getText().toString();
                 Contacts contacts=new Contacts();

                 if (!isValidEmail(email)){

                     dEmail.setError("Invalid email");

                 }else if (!isValidPassword(pass)) {

                    dPassword.setError("Invalid Password");

                }else if (TextUtils.isEmpty(name)) {

                    dName.setError("The item name cannot be empty");

                }else if (!mobile.matches(MobilePattern)) {

                    dNumber.setError("Please enter valid 10 digit phone number");

                }else if (!helper.checkUser(dEmail.getText().toString().trim())) {

                     contacts.setEmail(email);
                     contacts.setPassword(pass);
                     contacts.setName(name);
                     contacts.setMobile(mobile);

                     helper.insertUser(contacts);
                     dEmail.setText("");
                     dPassword.setText("");
                     dName.setText("");
                     dNumber.setText("");

                     Toast.makeText(getContext(), "user ragister successfully", Toast.LENGTH_SHORT).show();

                 } else {

                  Toast.makeText(getContext(), "Email id already exist", Toast.LENGTH_SHORT).show();
                }


            }


//                if (!isValidEmail(Email)) {
//                    dEmail.setError("Invalid Email");
//
//                }else {
//
//                    contacts.setEmail(Email);
//                }
//
//
//                if (!isValidPassword(pass)) {
//                    dPassword.setError("Invalid Password");
//                }else {
//
//                    contacts.setPassword(pass);
//                }
//
//
//                if (TextUtils.isEmpty(name)) {
//
//                    dName.setError("The item name cannot be empty");
//                }else {
//
//                    contacts.setName(name);
//                }
//
//                if (!mobile.matches(MobilePattern)) {
//
//                    dNumber.setError("Please enter valid 10 digit phone number");
//                }else {
//
//                    contacts.setMobile(mobile);
//                }

               // helper.insertUser(contacts);
               // Toast.makeText(getContext(), "user ragister successfully", Toast.LENGTH_SHORT).show();

            //}
        });
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}
