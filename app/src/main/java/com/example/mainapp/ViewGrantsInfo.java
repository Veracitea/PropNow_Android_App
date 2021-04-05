package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static java.lang.Boolean.FALSE;

public class ViewGrantsInfo extends AppCompatActivity {
    DrawerLayout drawerLayout;
    boolean loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loggedIn = FALSE;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grants_info);
        drawerLayout = findViewById(R.id.drawer_layout);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickEligibility(View view){
        if (loggedIn){MainActivity.redirectActivity(this,ViewEligibility.class);}
        else{MainActivity.login(this);}
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view){ MainActivity.openDrawer(drawerLayout); }

    //MAIN MENU
    public void ClickHome(View view){ MainActivity.redirectActivity(this,MainActivity.class); }
    public void ClickAdvFilters(View view){ MainActivity.redirectActivity(this,AdvancedFilters.class); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ recreate(); }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ MainActivity.redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){
        MainActivity.redirectActivity(this,MonicaGeller.class);
    }

    //HOME CALC
    public void ClickHomeCalculator(View view){ MainActivity.redirectActivity(this,HomeCalculator.class); }

    //MY LISTINGS
    public void ClickMyListings(View view){
        MainActivity.redirectActivity(this,MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view){
        MainActivity.redirectActivity(this,Inbox.class);
    }
    public void ClickEditInbox(View view){
        MainActivity.redirectActivity(this,EditInbox.class);
    }

    //SETTINGS
    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,Settings.class);
    }
}