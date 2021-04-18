package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class Chat extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton button;
    //getting domain and loggedIn status
    String domain = MainActivity.getDomain();
    boolean loggedIn = MainActivity.setLoggedIn();
    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        drawerLayout = findViewById(R.id.drawer_layout);

        button = findViewById(R.id.imageButton4);
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
            username.setText("Francisca Grand");
            picture1.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
        } else if (domain == "NON-AGENT") {  //for non-agents
            mylistings.setVisibility(View.GONE);
            username.setText("Ealasaid MacCarrane");
            picture2.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
        } else{  //for general users
            mylistings.setVisibility(View.GONE);
            inbox.setVisibility(View.GONE);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                             public boolean onMenuItemClick(MenuItem item) {
                                                 switch (item.getItemId()) {
                                                     case R.id.camera:
                                                         //Toast.makeText(Chat.this, "Keyword!", Toast.LENGTH_SHORT).show();
                                                         MainActivity.camera(Chat.this);
                                                         return true;
                                                     case R.id.photo:
                                                         // redirect to gallery
                                                         return true;
                                                     case R.id.doc:
                                                         // redirect to documents
                                                         return true;
                                                     case R.id.location:
                                                         // redirect to map
                                                         return true;
                                                     case R.id.contact:
                                                         // redirect to contacts
                                                         return true;
                                                     default:
                                                         return false;
                                                 }
                                             }
                                         });
    }


//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.camera:
//                System.out.println("CLICKED ON CAMERA");
//              //  MainActivity.camera(this);
//                MainActivity.redirectActivity(this,HomeCalculator.class);//change later!
//                return true;
////            case R.id.photo:
////                delete(item);
////                return true;
//            default:
//                recreate();
//                return false;
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);  // for the two icons in action bar
        return true;
    }






    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    //MAIN MENU
    public void ClickHome(View view){ MainActivity.redirectActivity(this,MainActivity.class); }
    public void ClickAdvFilters(View view){
        MainActivity.redirectActivity(this,AdvancedFilters.class);
    }
    public void ClickCamera(View view){ MainActivity.camera(this); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ MainActivity.redirectActivity(this,ViewGrantsInfo.class); }
    public void ClickEligibility(View view){
        MainActivity.redirectActivity(this,ViewEligibility.class);
        //else{MainActivity.login(this);}
    }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ MainActivity.redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){ MainActivity.redirectActivity(this,MonicaGeller.class); }

    //HOME CALC
    public void ClickHomeCalculator(View view){
        //this code below is correct
        if (domain == "AGENT" || domain == "NON-AGENT") {
            loggedIn = true;
        }
        else{ loggedIn=false;}
       MainActivity.redirectActivity(this,HomeCalculator.class);
       // else{MainActivity.login(this);}
    }

    //MY LISTINGS
    public void ClickMyListings(View view){
        MainActivity.redirectActivity(this,MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view){
        recreate();
    }
    public void ClickEditInbox(View view){
        MainActivity.redirectActivity(this,EditInbox.class);
    }

    public void ClickChat(View view){
        MainActivity.redirectActivity(this, Chat.class);
    }

    //SETTINGS
    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,Settings.class);
    }



    // SELF MADE FUNCTION




}
