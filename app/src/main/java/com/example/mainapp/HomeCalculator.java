package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.TextUtils;


import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.valueOf;

public class HomeCalculator extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton myimagebutton;
    Button mybutton;
    Spinner dropdown;
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
        setContentView(R.layout.activity_home_calculator);
        final EditText p = (EditText) findViewById(R.id.editTextNumber);
        final EditText i = (EditText) findViewById(R.id.textInputLayout1);
        final EditText y = (EditText) findViewById(R.id.editTextDate4);







        myimagebutton = (ImageButton) findViewById(R.id.imageButton2);
        dropdown = findViewById(R.id.spinner1);

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
        } else if (domain=="NON-AGENT"){  //for non-agents
            mylistings.setVisibility(View.GONE);
            username.setText("Rachel Green");
            picture2.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
        } else{  //for general users
            mylistings.setVisibility(View.GONE);
            inbox.setVisibility(View.GONE);
        }

        String[] items = new String[]{"Employee", "Employee (Pensionable)", "Self-Employed Person", "CPF not applicable"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentloadnewactivity = new Intent(HomeCalculator.this, Homecalc2.class);
                startActivity(intentloadnewactivity);


            }
        });
        mybutton = (Button) findViewById(R.id.buttoncalc2);

        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st1 = p.getText().toString();
                String st2 = i.getText().toString();
                String st3 = y.getText().toString();
                if (TextUtils.isEmpty(st1)) {
                    p.setError("Enter Prncipal Amount");
                    p.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(st2)) {
                    i.setError("Enter Interest Rate");
                    i.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(st3)) {
                    y.setError("Enter Years");
                    y.requestFocus();
                    return;
                }
                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);
                float Principal = calPric(p);
                float Rate = calInt(i);
                float Months = calMonth(y);
                float Dvdnt = calDvdnt(Rate, Months);
                float FD = calFinalDvdnt(Principal, Rate, Dvdnt);
                float D = calDivider(Dvdnt);
                float emi = calEmi(FD, D);
                float TA = calTa(emi, Months);
                float ti = calTotalInt(TA, Principal);
                float res = emi;
                float totint = ti;



                Intent intentloadnew = new Intent(HomeCalculator.this, Homecalc3.class);
                startActivity(intentloadnew);



            }
        });




        drawerLayout = findViewById(R.id.drawer_layout);
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
    public void ClickHomeCalculator(View view){ recreate(); }


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

    public float calPric(float p) {
        return (float)(p);
    }
    public float calInt(float i) {
        return (float)(i / 12 / 100);
    }
    public float calMonth(float y) {
        return (float)(y * 12);
    }
    public float calDvdnt(float Rate, float Months) {
        return (float)(Math.pow(1 + Rate, Months));
    }
    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
        return (float)(Principal * Rate * Dvdnt);
    }
    public float calDivider(float Dvdnt) {
        return (float)(Dvdnt - 1);
    }
    public float calEmi(float FD, Float D) {
        return (float)(FD / D);
    }
    public float calTa(float emi, Float Months) {
        return (float)(emi * Months);
    }
    public float calTotalInt(float TA, float Principal) {
        return (float)(TA - Principal);
    }

}
