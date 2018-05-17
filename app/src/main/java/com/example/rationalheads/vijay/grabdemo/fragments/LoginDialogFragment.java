package com.example.rationalheads.vijay.grabdemo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.activites.MainActivity;
import com.example.rationalheads.vijay.grabdemo.databasehelpers.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginDialogFragment extends Fragment {

    private EditText lEmail,lPass;
    private Button lLogin;
    private TextView forgot_link;
    private  View lview;
    DatabaseHelper helper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        helper = new DatabaseHelper(getContext());
        // Inflate the layout for this fragment
        lview=inflater.inflate(R.layout.fragment_login_dialog,container,false);

        //initialization of all field
        intiloginfield();

        return lview;
    }

    private void intiloginfield() {

        lEmail=lview.findViewById(R.id.lemail);
        lPass=lview.findViewById(R.id.lpass);
        lLogin=lview.findViewById(R.id.input_login);

        lLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                 String email=lEmail.getText().toString();
                 String password = lPass.getText().toString();

                String pass=helper.searchUser(email);

                if (lEmail.getText().toString().length() < 0 || lPass.getText().toString().length() < 0){

                    Toast.makeText(getContext(), "Please fill the record", Toast.LENGTH_SHORT).show();

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

//    // validating email id
//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    // validating password with retype password
//    private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() > 6) {
//            return true;
//        }
//        return false;
//    }

}
