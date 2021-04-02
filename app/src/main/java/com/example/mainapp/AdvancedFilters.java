package com.example.mainapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Collections;

public class AdvancedFilters extends AppCompatActivity {

    DrawerLayout drawerLayout;
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


        //primary select box
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



    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickBackBtn(View view){
        MainActivity.redirectActivity(AdvancedFilters.this, MainActivity.class);
    }

    public void ClickApply(View view) {
        MainActivity.redirectActivity(AdvancedFilters.this, ResultsPage.class);
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view){ MainActivity.openDrawer(drawerLayout); }

    //MAIN MENU
    public void ClickHome(View view){ MainActivity.redirectActivity(this,MainActivity.class); }
    public void ClickAdvFilters(View view){ recreate(); }

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view){ MainActivity.redirectActivity(this,ViewGrantsInfo.class); }

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
