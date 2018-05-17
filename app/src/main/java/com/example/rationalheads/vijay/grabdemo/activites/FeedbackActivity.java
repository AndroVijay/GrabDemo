package com.example.rationalheads.vijay.grabdemo.activites;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.fragments.AlertDialogForLogin;
import com.example.rationalheads.vijay.grabdemo.fragments.FeedbackDialog;

public class FeedbackActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button submit;
    private Toolbar toolbar;
    private ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar=findViewById(R.id.feed_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        submit=findViewById(R.id.submitBtn);
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.clearCheck();


         /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb =  group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){

                    Toast.makeText(FeedbackActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {


                    FragmentManager manager=getSupportFragmentManager();
                    FeedbackDialog forLogin=new FeedbackDialog();
                    forLogin.show(manager,"fragment");





                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });

    }
}
