package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button SearchBar;
    ImageView houseInfo;

    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;

    //for login
    boolean loggedIn = FALSE;
    String domain = "AGENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addListenerOnButton();
        drawerLayout = findViewById(R.id.drawer_layout);


        //for sidebar - show options by domain
        mainmenu = findViewById(R.id.mainmenu);
        viewgrants = findViewById(R.id.viewgrants);
        viewagentinfo = findViewById(R.id.viewagentinfo);
        homecalc = findViewById(R.id.homecalc);
        mylistings = findViewById(R.id.mylistings);
        inbox = findViewById(R.id.inbox);
        settings = findViewById(R.id.settings);
        username = findViewById(R.id.username);
        picture = findViewById(R.id.picture);
        picture1 = findViewById(R.id.picture1);
        picture1.setVisibility(View.GONE);
        picture2 = findViewById(R.id.picture2);
        picture2.setVisibility(View.GONE);

        //set visibility according to domain
        if (domain=="AGENT"){  //for agents
            viewgrants.setVisibility(View.GONE);
            homecalc.setVisibility(View.GONE);
            viewagentinfo.setVisibility(View.GONE);
            username.setText("Monica Geller\nAgent ID: U273849K");
            picture1.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
            loggedIn = true;
        } else if (domain=="NON-AGENT"){  //for non-agents
            mylistings.setVisibility(View.GONE);
            username.setText("Rachel Green");
            picture2.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
            loggedIn = true;
        } else{  //for general users
            mylistings.setVisibility(View.GONE);
            inbox.setVisibility(View.GONE);
            loggedIn = false;
        }


        //buttons in main activity
        SearchBar = findViewById(R.id.button);
        houseInfo = findViewById(R.id.imageView2);
        SearchBar.setOnClickListener(new View.OnClickListener() { //clicking on search bar
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_search_bar);
            }
        });

        houseInfo.setOnClickListener(new View.OnClickListener() { //clicking on house image
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_house_info);
            }
        });

    }

    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open - close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    //login permission popup
    public static void login(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Login Required");
        builder.setMessage("Please login to unlock feature");
        //login
        builder.setPositiveButton("Login/Sign Up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //activity.finishAffinity();
                //REDIRECT TO LOGIN PAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!
                redirectActivity(activity,Login.class);
            }
        });
//        //back
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    //camera permission popup
    public static void camera(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Permission Required");
        builder.setMessage("This app wants to have access to your Camera and Location.");
        //login
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //redirect to CAMERA!!!!!!!!!!!
                redirectActivity(activity,HomeCalculator.class);
            }
        });
//        //back
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    //MAIN MENU
    public void ClickHome(View view){ recreate(); }
    public void ClickAdvFilters(View view){
        redirectActivity(this,AdvancedFilters.class);
    }
    public void ClickCamera(View view){ camera(this); }
    public void ClickLogin(View view){redirectActivity(this,Login.class);};

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ redirectActivity(this,ViewGrantsInfo.class); }
    public void ClickEligibility(View view){
        if (loggedIn){redirectActivity(this,ViewEligibility.class);}
        else{login(this);}
    }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){ redirectActivity(this,MonicaGeller.class); }

    //HOME CALC
    public void ClickHomeCalculator(View view){
        //this code below is correct
        if (loggedIn){redirectActivity(this,HomeCalculator.class);}
        else{login(this);}
    }

    //MY LISTINGS
    public void ClickMyListings(View view){
        redirectActivity(this,MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view){
        redirectActivity(this,Inbox.class);
    }
    public void ClickEditInbox(View view){
        redirectActivity(this,EditInbox.class);
    }

    //SETTINGS
    public void ClickSettings(View view){
        redirectActivity(this,Settings.class);
    }


}

