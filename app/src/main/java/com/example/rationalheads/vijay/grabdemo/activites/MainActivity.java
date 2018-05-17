package com.example.rationalheads.vijay.grabdemo.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.databasehelpers.DatabaseHelper;
import com.example.rationalheads.vijay.grabdemo.fragments.HomeFragment;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private MaterialSearchView materialSearchView;
    public WindowManager mWindowManager;
    private Button signin;
    private TextView fbProfileName;
    private ImageView fbProfile;
    public static String jsondata;
    private String[] list;
    private JSONObject response;
   // private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseHelper helper;
    private String email;
    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabSave;
    private LinearLayout layoutFabEdit;
    private LinearLayout layoutFabPhoto;
    private long lastPressTime;
    private static final int PIRIOD=2000;


   //private View bckgroundDimmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        // Manually checking internet connection
       // checkConnection();


        // helper = new DatabaseHelper(getApplicationContext());

        //sharedPreferences = getSharedPreferences("myprivatedata", 0);

        list = new String[]{"Alpha", "Beta", "Cupcake", "Eclair", "Froyo", "Ginger", "Honycomp", "ICS"};
        // for toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);


        //get data from database
        //Intent intent=getIntent();
        //email=intent.getStringExtra("Email");


        // for drawer
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        //home fragment
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

        // floatingActionButton

       // bckgroundDimmer = findViewById(R.id.semi_white_bg);
        fabSettings = findViewById(R.id.fabSetting);

        layoutFabSave = findViewById(R.id.layoutFabSave);
        layoutFabEdit = findViewById(R.id.layoutFabEdit);
        layoutFabPhoto =findViewById(R.id.layoutFabPhoto);

//        boolean hashLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
//
//        if (hashLoggedIn) {
//
//            String name = sharedPreferences.getString("Profile_Name", "");
//            String image = sharedPreferences.getString("Profile_Image", "");
//
//            fbProfileName.setText(name);
//            Glide.with(this).load(image).into(fbProfile);
//            //signin.setVisibility(View.INVISIBLE);
//            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
//
//        } else {
//

        //navigatinheader
        initNavigationHaeder();


        // gmail code..
        getgoogledata();

        // faccebook code...
        setUserProfile();

        // }

        //get data from database

        userdatafromdatabase();


        //for search icon
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        materialSearchView = new MaterialSearchView(this);
        materialSearchView = findViewById(R.id.materialsearch);
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);


        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //create your filter here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // you can  make change realtime  if you are typing here

                return false;
            }
        });


    //This gives FAB (Settings) open/close behavior
        fabSettings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (fabExpanded == true){
                closeSubMenusFab();

            } else {
                openSubMenusFab();
            }
        }
    });
    //Only main FAB is visible in the beginning
    closeSubMenusFab();
 }

//    private void checkConnection() {
//
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        showSnack(isConnected);
//    }

//    private void showSnack(boolean isConnected) {
//
//        String message;
//        int color;
//        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
//        } else {
//            message = "Sorry! Not connected to internet";
//            color = Color.RED;
//        }
//
//        Snackbar snackbar = Snackbar.make(findViewById(R.id.fabSetting), message, Snackbar.LENGTH_LONG);
//
//        View sbView = snackbar.getView();
//        TextView textView =  sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(color);
//        snackbar.show();
//    }

    private void openSubMenusFab() {

        layoutFabSave.setVisibility(View.VISIBLE);
        layoutFabEdit.setVisibility(View.VISIBLE);
        layoutFabPhoto.setVisibility(View.VISIBLE);

        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
       // bckgroundDimmer.setVisibility(View.VISIBLE);

    }

    private void closeSubMenusFab() {

        layoutFabSave.setVisibility(View.INVISIBLE);
        layoutFabEdit.setVisibility(View.INVISIBLE);
        layoutFabPhoto.setVisibility(View.INVISIBLE);

        fabSettings.setImageResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;

       // bckgroundDimmer.setVisibility(View.GONE);

    }


    private void userdatafromdatabase() {


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");



        //fbProfileName.setText(name);


    }


    //google login
    private void getgoogledata() {


        Intent intent = getIntent();
        String name1 = intent.getStringExtra("p_name");
        // String email1 = intent.getStringExtra("p_email");
        String image1 = intent.getStringExtra("p_url");

        fbProfileName.setText(name1);

        Glide.with(this).load(image1).into(fbProfile);


    }

    // facebook login
    private void setUserProfile() {

        Intent in = getIntent();
        jsondata = in.getStringExtra("json_data");

        try {

            response = new JSONObject(jsondata);

            String Fname = response.get("name").toString();
            String url = response.getJSONObject("picture").getJSONObject("data").getString("url");
            //_gender = response.get("gender").toString();
            // String gender = response.asMap().get("gender").toString();
            //user_email.setText(response.get("email").toString());
            //user_name.setText(response.get( "name").toString());
            //_id =response.get("id").toString();
            // Log.e("Id",_id);
            //_email = response.get("email").toString();
            // user_email.setText(_email);

            fbProfileName.setText(Fname);
            Glide.with(this).load(url).into(fbProfile);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    private void initNavigationHaeder() {


        // View header = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        // navigationView.addHeaderView(header);
        View header = navigationView.getHeaderView(0);
        signin = header.findViewById(R.id.btn_signin);
        fbProfileName = header.findViewById(R.id.profile_name);
        fbProfile = header.findViewById(R.id.circleImageView);


        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                drawerLayout.closeDrawer(Gravity.START);

            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.search);

        materialSearchView.setMenuItem(item);


//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.notification:

                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        Fragment fragment = null;

        switch (id) {

            case R.id.nav_home:


                fragment = new HomeFragment();

                drawerLayout.closeDrawer(Gravity.START);

                break;
//            case R.id.nav_signin:
//
//                Intent in = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(in);
//                drawerLayout.closeDrawer(Gravity.START);
//                break;
            case R.id.nav_share:

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\n Let me recommend you this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/GrabDemo\n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                    drawerLayout.closeDrawer(Gravity.START);
                    break;
                } catch (Exception e) {

                    e.printStackTrace();
                }

                break;

            case R.id.nav_notification:

                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.nav_logout:

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                        Toast.makeText(MainActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        LoginManager.getInstance().logOut();
                        startActivity(intent);
                        finish();

                    }
                });
                break;

            case R.id.nav_Help:

                Intent in = new Intent(MainActivity.this, HelpSuportActivity.class);
                startActivity(in);

                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.nav_feedback:

                Intent feedback = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(feedback);
                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_grab_on_day:

                Intent day = new Intent(MainActivity.this, GrabDemoDayActivity.class);
                startActivity(day);

                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_setting:

                Intent setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(setting);
                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_rate_app:

               Intent rating=new Intent(MainActivity.this,AppRatingActivity.class);
               startActivity(rating);
               drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_festival_offer:

                Intent fest=new Intent(MainActivity.this, FestivalOffersActivity.class);
                startActivity(fest);
                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_brand:

                Intent bank=new Intent(MainActivity.this,BankOffersActivity.class);
                startActivity(bank);
                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.nav_coupon_finder:

                Intent finder=new Intent(MainActivity.this,CouponFinderActivity.class);
                startActivity(finder);
                drawerLayout.closeDrawer(Gravity.START);
                break;




        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            //Set menuItem checked and title
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);


        } else {
            
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        
        if (event.getKeyCode()== KeyEvent.KEYCODE_BACK){
            
            switch (event.getAction()){
                
                case KeyEvent.ACTION_DOWN:
                    
                    if (event.getDownTime() - lastPressTime< PIRIOD){
                        
                        finish();

                    }else {

                        Toast.makeText(this, "Press Back again to Exit", Toast.LENGTH_SHORT).show();
                        lastPressTime =event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }
        mGoogleApiClient.connect();
        super.onResume();


    }



    @Override
    protected void onPause() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }


}
