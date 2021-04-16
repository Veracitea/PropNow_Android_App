package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class AddListings extends AppCompatActivity {

    //references to buttons and other controls on layout
    DrawerLayout drawerLayout;
    ListView lv_houseList;


    EditText et_blockNum, et_street, et_story, et_town, et_bedroomNum, et_floorArea, et_flatModel, et_leaseCommencement, et_remainingLease, et_resalePrice;
    ImageButton btn_addImage;
    Button btn_addListing;




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
        setContentView(R.layout.activity_add_listings);
        drawerLayout = findViewById(R.id.drawer_layout);

        et_blockNum = findViewById(R.id.et_blockNum);
        et_street = findViewById(R.id.et_street);
        et_story = findViewById(R.id.et_story);
        et_town = findViewById(R.id.et_town);
        et_bedroomNum = findViewById(R.id.et_bedroomNum);
        et_floorArea = findViewById(R.id.et_floorArea);
        et_flatModel = findViewById(R.id.et_flatModel);
        et_leaseCommencement = findViewById(R.id.et_leaseCommencement);
        et_remainingLease = findViewById(R.id.et_remainingLease);
        et_resalePrice = findViewById(R.id.et_resalePrice);

        btn_addImage = findViewById(R.id.btn_addImage);
        btn_addListing = findViewById(R.id.btn_addListing);




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

        //button listeners for the add and view all buttons
        btn_addListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //House house;//= new House(0,"",0,"","","","","",0,"",0,130);

                House house;


                try {
                     house = new House(-1,
                            et_town.getText().toString(),
                            et_bedroomNum.getText().toString(),
                            et_blockNum.getText().toString(),
                            et_street.getText().toString(),
                            et_story.getText().toString(),
                            Integer.parseInt(et_floorArea.getText().toString()),
                            et_flatModel.getText().toString(),
                            Integer.parseInt(et_leaseCommencement.getText().toString()),
                            et_remainingLease.getText().toString(),
                            Integer.parseInt(et_resalePrice.getText().toString()),
                            130

                    );

                    HouseDatabaseHelper houseDatabaseHelper = new HouseDatabaseHelper(AddListings.this);
                    boolean success = houseDatabaseHelper.addOne(house);
                    Toast.makeText(AddListings.this, "Listing successfully added!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddListings.this, MyListings.class);
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println(e);
                    Toast.makeText(AddListings.this, "Invalid! Please fill in all fields", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    public void ClickBack(View view){
        Intent intent = new Intent(this, MyListings.class);
        startActivity(intent);
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
    public void ClickMyListings(View view){MainActivity.redirectActivity(this,MyListings.class);}
    public void ClickAddListing(View view){recreate();}

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