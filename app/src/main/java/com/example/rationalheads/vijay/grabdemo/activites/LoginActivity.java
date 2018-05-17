package com.example.rationalheads.vijay.grabdemo.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.adapters.SignupDialogViewPagerAdapter;
import com.example.rationalheads.vijay.grabdemo.fragments.AlertDialogForLogin;
import com.example.rationalheads.vijay.grabdemo.fragments.SignupLoginFragmentDialog;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity  implements  GoogleApiClient.OnConnectionFailedListener{

    private Button btn_signup, btn_login;
    private TextView skip;
    private View mView;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    private GoogleSignInOptions googleSignInOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        facebookSDKInitialize();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email"));
        getLoginDetails(loginButton);



        //googleInitialization();
        setgoogleOption();
        signInButton = findViewById(R.id.google_button);
        // signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        //signInButton.setScopes(googleSignInOptions.getScopeArray());
        //signInButton=findViewById(R.id.signin);
        //getgoogleloginDetails(signInButton);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//
//        callbackManager = CallbackManager.Factory.create();

//        txtUsername = findViewById(R.id.input_email);
//        txtUserPassword = findViewById(R.id.input_password);
//        btnsignin = findViewById(R.id.btn_login);
//        link_signup = findViewById(R.id.link_signup);
//        btnsignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = txtUsername.getText().toString();
//                String password = txtUserPassword.getText().toString();
//                String pass = helper.searchUser(username);
//
//                if (txtUsername.getText().toString().length() == 0 || txtUserPassword.getText().toString().length() == 0) {
//                    Toast.makeText(HomeActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
//                } else if (password.equals(pass)) {
//                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
//                    intent.putExtra("username", username);
//                    startActivity(intent);
//
//                } else {
//                    Toast.makeText(HomeActivity.this, "username and password not match", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

//        link_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(HomeActivity.this, SignupActivity.class);
//                startActivity(intent);
//
//
//            }
//        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signin, RC_SIGN_IN);


            }
        });

        // all button initialization
        initbuttons();
    }

    private void setgoogleOption() {


        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }


    private void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void getLoginDetails(LoginButton loginButton) {


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                getUserInfo(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void getUserInfo(LoginResult loginResult) {


        GraphRequest request=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {


                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("json_data",object.toString());
                startActivity(intent);
            }
        });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", " id,name,email,picture.width(120).height(120),gender");
        request.setParameters(permission_param);
        request.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case RC_SIGN_IN:

                GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = googleSignInResult.getSignInAccount();

                if (requestCode == RC_SIGN_IN) {

                    try {


                        Intent sendData = new Intent(LoginActivity.this, MainActivity.class);
                        String name, email, dpUrl = "";
                        name = account.getDisplayName();
                        email = account.getEmail();

                        //  String link=account.getPhotoUrl().toString();
                        //dpUrl = account.getPhotoUrl().toString();
                        dpUrl = account.getPhotoUrl() != null ? account.getPhotoUrl().toString() : null;
                        sendData.putExtra("p_name", name);
                        sendData.putExtra("p_email", email);
                        sendData.putExtra("p_url", dpUrl);



                        startActivity(sendData);

                    } catch (Exception e) {

                        Toast.makeText(LoginActivity.this,"Login Denied", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(LoginActivity.this, " Login Failed ", Toast.LENGTH_SHORT).show();

                }

                break;
        }


    }

    private void initbuttons() {

        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);
        skip = findViewById(R.id.skip_to_login);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sign up popup
                FragmentManager fm = getSupportFragmentManager();
                SignupLoginFragmentDialog overlay = new SignupLoginFragmentDialog();
                overlay.show(fm, "Fragment");


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //popup();

                FragmentManager manager=getSupportFragmentManager();
                AlertDialogForLogin forLogin=new AlertDialogForLogin();
                forLogin.show(manager,"fragment");

            }
        });

    }

    private void popup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        mView = getLayoutInflater().inflate(R.layout.fragment_login_dialog, null);
        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
