package com.example.mainapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.slider.RangeSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;

public class AdvancedFilters extends AppCompatActivity {

    DrawerLayout drawerLayout;
    List<House> HouseList = new ArrayList<>();

    //getting domain and loggedIn status
    String domain = MainActivity.getDomain();
    boolean loggedIn = MainActivity.setLoggedIn();
    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;

    Button Button;
    CheckBox cbPrice, cbRooms, cbLease, cbStorey;
    TextView primary,secondary;
    RangeSlider rsPrice, rsRooms, rsLease, rsStorey;
    boolean[] selectedCat;
    ArrayList<Integer> options = new ArrayList<>();
    ArrayList<Integer> options1 = new ArrayList<>();
    String[] optionsArray = {"Price","Rooms","Remaining Lease","Storey Range"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_filters);
        drawerLayout = findViewById(R.id.drawer_layout);

//        System.out.println(domain);
//        System.out.println(loggedIn);
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
        if (domain == "AGENT") {  //for agents
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
        } else {  //for general users
            mylistings.setVisibility(View.GONE);
            inbox.setVisibility(View.GONE);
        }


        //multislect boxes
        primary = findViewById(R.id.multiselect);
        secondary = findViewById(R.id.multiselect2);
        //rangesliders
        rsPrice = findViewById(R.id.sliderPrice);
        rsLease = findViewById(R.id.sliderLease);
        rsStorey = findViewById(R.id.sliderStorey);
        rsRooms = findViewById(R.id.sliderBedrooms);
        //checkboxes
        cbPrice = findViewById(R.id.price);
        cbRooms = findViewById(R.id.bedrooms);
        cbLease = findViewById(R.id.lease);
        cbStorey = findViewById(R.id.storey);


        selectedCat = new boolean[optionsArray.length];

        readHouse();

        ArrayList<String> list = new ArrayList<>();
        
        Button = findViewById(R.id.button);
        
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float minPrice, maxPrice, minFloor, maxFloor, minLease, maxLease, minBed, maxBed;
                if (cbPrice.isChecked()) {
                    List<Float> xPrice = rsPrice.getValues();
                    minPrice = xPrice.get(0);
                    maxPrice = xPrice.get(1);
                } else {
                    minPrice = 0;
                    maxPrice = 1153;
                }

                if (cbStorey.isChecked()) {
                    List<Float> xFloor = rsStorey.getValues();
                    minFloor = xFloor.get(0);
                    maxFloor = xFloor.get(1);
                } else {
                    minFloor = 40;
                    maxFloor = 162;
                }

                if (cbLease.isChecked()) {
                    List<Float> xLease = rsLease.getValues();
                    minLease = xLease.get(0);
                    maxLease = xLease.get(1);
                } else {
                    minLease = 1967;
                    maxLease = 2021;
                }

                if (cbRooms.isChecked()) {
                    List<Float> xBed = rsRooms.getValues();
                    minBed = xBed.get(0);
                    maxBed = xBed.get(1);
                } else {
                    minBed = 1;
                    maxBed = 7;
                }

                for (House h : HouseList) {
                    int housePrice = h.getResale_price();
                    int houseFloor = h.getFloor_area_sqm();
                    int houseLease = h.getLease_commence_date();
                    int houseBed = h.getBedroom();
                    String id = h.getHouseId();
                    if ((housePrice >= minPrice * 1000) && (housePrice <= maxPrice * 1000) && (houseFloor >= minFloor)
                            && (houseFloor <= maxFloor) && (houseLease >= minLease) && (houseLease <= maxLease) &&
                            (houseBed >= minBed) && (houseBed <= maxBed)) {
                        list.add(id);
                    }
                }
                search(list);
                Intent intent = new Intent(AdvancedFilters.this, ResultsPage.class);
                startActivity(intent);
                System.out.println("Hello");
            }

        });

        primary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        com.example.mainapp.AdvancedFilters.this
                );
                builder.setTitle("Select Filters");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(optionsArray, selectedCat, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            //when checkbox is selected
                            options.add(i);

                            //sort list
                            Collections.sort(options);
                        }else{
                            //when checkbox is unselected
                            options.remove(i);

                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j=0;j<options.size();j++){
                            stringBuilder.append(optionsArray[options.get(j)]);

                            if (j != options.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        primary.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }

        });


        //secondary select box
        secondary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(
                        com.example.mainapp.AdvancedFilters.this
                );
                builder1.setTitle("Select Filters");
                builder1.setCancelable(false);

                builder1.setMultiChoiceItems(optionsArray, selectedCat, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            //when checkbox is selected
                            options1.add(i);

                            //sort list
                            Collections.sort(options);
                        }else{
                            //when checkbox is unselected
                            options1.remove(i);

                        }
                    }
                });
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder1 = new StringBuilder();

                        for (int j=0;j<options1.size();j++){
                            stringBuilder1.append(optionsArray[options1.get(j)]);

                            if (j != options1.size()-1){
                                stringBuilder1.append(", ");
                            }
                        }
                        secondary.setText(stringBuilder1.toString());
                    }
                });

                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder1.show();
            }
        });



            //CHECKBOXES

        rsRooms.setEnabled(false);
        rsLease.setEnabled(false);
        rsPrice.setEnabled(false);
        rsStorey.setEnabled(false);


            //Once checkbox ticked - activate respective seekBar
        cbPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( ((CheckBox)v).isChecked() ) {rsPrice.setEnabled(true);}
                    else{rsPrice.setEnabled(false);}
                }
            });

        cbRooms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( ((CheckBox)v).isChecked() ) {rsRooms.setEnabled(true);}
                    else{rsRooms.setEnabled(false);}
                }
            });

        cbStorey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( ((CheckBox)v).isChecked() ) {rsStorey.setEnabled(true);}
                    else{rsStorey.setEnabled(false);}
                }
            });

        cbLease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( ((CheckBox)v).isChecked() ) {rsLease.setEnabled(true);}
                    else {rsLease.setEnabled(false);}
                }
            });


        }


    public void search(ArrayList<String> list){
        Intent intent = new Intent(AdvancedFilters.this, ResultsPage.class);
        intent.putExtra("thelist", list);
        startActivity(intent);
    }

        //primary select box


    public void ClickBackBtn(View view){
        MainActivity.redirectActivity(AdvancedFilters.this, MainActivity.class);
    }

    public void ClickApply(View view) {
        MainActivity.redirectActivity(AdvancedFilters.this, ResultsPage.class);
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
    public void ClickAdvFilters(View view){ recreate(); }
    public void ClickCamera(View view){ MainActivity.camera(this); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ MainActivity.redirectActivity(this,ViewGrantsInfo.class); }


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

    private void readHouse(){
        InputStream isss = getResources().openRawResource(R.raw.housess); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(isss, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                String[] tokens = line.split(",");

                House houses = new House();
                houses.setAgent_id(Integer.parseInt(tokens[0]));
                //houses.setMonth(tokens[1]);
                houses.setTown(tokens[2]);
                houses.setFlat_type(tokens[3]);
                houses.setBlock(tokens[4]);
                houses.setStreet_name(tokens[5]);
                houses.setStorey_range(tokens[6]);
                houses.setFloor_area_sqm(Integer.parseInt(tokens[7]));
                houses.setFlat_model(tokens[8]);
                houses.setLease_commence_date(Integer.parseInt(tokens[9]));
                houses.setRemaining_lease(tokens[10]);
                houses.setResale_price(Integer.parseInt(tokens[11]));

                HouseList.add(houses);
                Log.d("MyActivity", "Just Created: " + houses);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading on Line: " + line, e);
            e.printStackTrace();
        }
    }
}
