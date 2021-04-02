package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton ARcamera;
    Button SearchBar;
   // TextView filters;
    //ImageButton filters2;
    ImageView houseInfo;
    ToggleButton favorites;
    //for login
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loggedIn = TRUE;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addListenerOnButton();
        drawerLayout = findViewById(R.id.drawer_layout);

        ARcamera = findViewById(R.id.imageButton11);
        SearchBar = findViewById(R.id.button);
       // filters = findViewById(R.id.textView3);
        // filters2 = findViewById(R.id.imageButton3);
        houseInfo = findViewById(R.id.imageView2);
        favorites = findViewById(R.id.toggleButton2);

// change all this to redirect activity
        ARcamera.setOnClickListener(new View.OnClickListener() { //clicking on AR camera button
            @Override
            public void onClick(View v) { setContentView(R.layout.activity_a_r_search_permission);
            }
        });
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
        favorites.setOnClickListener(new View.OnClickListener() { //clicking on filter favorites
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_favorites);
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

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }

    //login permission popup
    private static void login(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Login Required");
        builder.setMessage("Please login to unlock feature");
        //login
        builder.setPositiveButton("Login/Sign Up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                //REDIRECT TO LOGIN PAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!
                redirectActivity(activity,HomeCalculator.class);
            }
        });
        //back
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
            }
        });

        builder.show();
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

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){
        redirectActivity(this,ViewGrantsInfo.class);
    }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){
        redirectActivity(this,MonicaGeller.class);
    }

    //HOME CALC

    public void ClickHomeCalculator(View view, boolean loggedIn){
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