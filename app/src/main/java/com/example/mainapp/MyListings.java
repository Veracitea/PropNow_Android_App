package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.view.View.VISIBLE;

public class MyListings extends AppCompatActivity {
    DrawerLayout drawerLayout;
    //getting domain and loggedIn status
    String domain = MainActivity.getDomain();
    boolean loggedIn = MainActivity.setLoggedIn();
    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;
    Button btn_refresh, button;
    RecyclerView lv_listings;
    ImageButton  del;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<House> returnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);
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
        btn_refresh = findViewById(R.id.btn_refresh);
        lv_listings = findViewById(R.id.lv_listings);
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
        //HouseDatabaseHelper houseDatabaseHelper = new HouseDatabaseHelper(MyListings.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyListings.this, EditListings.class);
                startActivity(intent);
            }

        });

        recyclerView = findViewById(R.id.lv_listings);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(returnList,MyListings.this);
        recyclerView.setAdapter(mAdapter);


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseDatabaseHelper houseDatabaseHelper = new HouseDatabaseHelper(MyListings.this);
                List<House> all = houseDatabaseHelper.getAll();

                ArrayAdapter houseArrayAdapter = new ArrayAdapter<House>(MyListings.this, android.R.layout.simple_list_item_1,all);
                //lv_listings.setAdapter(houseArrayAdapter);
            }
        });

//        lv_listings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                House clickedhouse = (House) parent.getItemAtPosition(position);
//                HouseDatabaseHelper houseDatabaseHelper = new HouseDatabaseHelper(MyListings.this);
//                houseDatabaseHelper.DeleteOne(clickedhouse);
//
//                ArrayAdapter houseArrayAdapter = new ArrayAdapter<House>(MyListings.this, android.R.layout.simple_list_item_1, (List<House>) houseDatabaseHelper);
//                lv_listings.setAdapter(houseArrayAdapter);
//                Toast.makeText(MyListings.this, "deleted house ID" + clickedhouse.getId(), Toast.LENGTH_SHORT).show();
//            }
//        });








//        House clickedhouse = (House) parent.getItemAtPosition(position);
//        HouseDatabaseHelper.DeleteOne(clickedhouse);
//        Toast.makeText(MyListings.this, "Deleted " + clickedhouse.toString(), Toast.LENGTH_SHORT).show()

        del = (ImageButton) findViewById(R.id.imageButton3);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentloadnewactivity1 = new Intent(MyListings.this, DeleteListings.class);
                startActivity(intentloadnewactivity1);


            }
        });

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
    public void ClickMyListings(View view){recreate(); }
    public void ClickAddListing(View view){MainActivity.redirectActivity(this,AddListings.class);}

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