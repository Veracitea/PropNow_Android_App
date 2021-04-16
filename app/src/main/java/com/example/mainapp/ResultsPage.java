package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;

public class ResultsPage extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;
    DrawerLayout drawerLayout;
    //getting domain and loggedIn status
    String domain = MainActivity.getDomain();
    boolean loggedIn = MainActivity.setLoggedIn();
    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;

    ArrayAdapter<String> adapter;

    ImageButton ARcamera;
    Button SearchBar;
    TextView filters;
    ImageButton filters2;
    ImageView houseInfo;
//    ToggleButton favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        drawerLayout = findViewById(R.id.drawer_layout);

        //ARcamera = findViewById(R.id.imageButton11);
        SearchBar = findViewById(R.id.button);
        filters = findViewById(R.id.textView3);
        filters2 = findViewById(R.id.imageButton3);
        houseInfo = findViewById(R.id.imageView);
//        favorites = findViewById(R.id.toggleButton2);
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


        SearchBar.setOnClickListener(new View.OnClickListener() { //clicking on search bar
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_search_bar);
            }
        });
        filters.setOnClickListener(new View.OnClickListener() { //clicking on advanced filters (text)
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_advanced_filters);
            }
        });
        filters2.setOnClickListener(new View.OnClickListener() { //clicking on advanced filters(button)
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_advanced_filters);
            }
        });
        /*houseInfo.setOnClickListener(new View.OnClickListener() { //clicking on house image
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_house_info);
            }
        });*/

        myList = (ListView)findViewById(R.id.MyList);

        ArrayList<String> newlist = (ArrayList<String>) getIntent().getSerializableExtra("thelist");

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,newlist);
        myList.setAdapter(adapter);

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
        //Intent intent = new Intent(ResultsPage.this, AdvancedFilters.class);
        //this.startActivity(intent);
        MainActivity.redirectActivity(this,AdvancedFilters.class);
        //this.finish();
    }
    public void ClickCamera(View view){ MainActivity.camera(this); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ MainActivity.redirectActivity(this,ViewGrantsInfo.class); }
    public void ClickEligibility(View view){
        if (loggedIn){MainActivity.redirectActivity(this,ViewEligibility.class);}
        else{MainActivity.login(this);}
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
        if (loggedIn){MainActivity.redirectActivity(this,HomeCalculator.class);}
        else{MainActivity.login(this);}
    }

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