package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class MonicaGeller extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monica_geller);
        drawerLayout = findViewById(R.id.drawer_layout);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view){ MainActivity.openDrawer(drawerLayout); }

    //MAIN MENU
    public void ClickHome(View view){ MainActivity.redirectActivity(this,MainActivity.class); }
    public void ClickAdvFilters(View view){ MainActivity.redirectActivity(this,AdvancedFilters.class); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ MainActivity.redirectActivity(this,ViewGrantsInfo.class); }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ MainActivity.redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){
        recreate();
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
        recreate();
    }

    //SETTINGS
    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,Settings.class);
    }
}