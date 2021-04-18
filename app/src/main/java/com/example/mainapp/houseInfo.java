package com.example.mainapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class houseInfo extends AppCompatActivity {
    DrawerLayout drawerLayout;
    //getting domain and loggedIn status
    String domain = MainActivity.getDomain();
    boolean loggedIn = MainActivity.setLoggedIn();
    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username, agent, mrt, bedroom, street,price;
    ImageView picture,picture1,picture2, house;
    static String Agent,MRT,Bedroom,Price,Street = " ";
    static int Photo;


    public static void setAgent(String a){
        Agent = "Agent: "+a;
    }
    public static void setMRT(String a){
        MRT = "Nearest MRT: "+a;
    }
    public static void setBedroom(int a){
        Bedroom = "No. of bedrooms: "+a;
    }
    public static void setStreet(String a){
        Street = a;
    }
    public static void setPrice(int a){
        Price = "Resale Price($): "+a;
    }
    public static void setImage(int picture){Photo = picture;};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
        drawerLayout = findViewById(R.id.drawer_layout);
        agent = findViewById(R.id.agent);
        mrt = findViewById(R.id.mrt);
        bedroom = findViewById(R.id.bedroom);
        street = findViewById(R.id.street);
        price = findViewById(R.id.price);
        house = findViewById(R.id.house);

        agent.setText(Agent);
        mrt.setText(MRT);
        bedroom.setText(Bedroom);
        street.setText(Street);
        price.setText(Price);
        house.setImageResource(Photo);

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
        picture1.setVisibility(GONE);
        picture2 = findViewById(R.id.picture2);
        picture2.setVisibility(GONE);

        //set visibility according to domain
        if (domain=="AGENT"){  //for agents
            viewgrants.setVisibility(GONE);
            homecalc.setVisibility(GONE);
            viewagentinfo.setVisibility(GONE);
            username.setText("Francisca Grand");
            picture1.setVisibility(VISIBLE);
            picture.setVisibility(GONE);
        } else if (domain == "NON-AGENT") {  //for non-agents
            mylistings.setVisibility(GONE);
            username.setText("Ealasaid MacCarrane");
            picture2.setVisibility(VISIBLE);
            picture.setVisibility(GONE);
        } else{  //for general users
            mylistings.setVisibility(GONE);
            inbox.setVisibility(GONE);
        }
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
    public void ClickMyListings(View view){
        MainActivity.redirectActivity(this,MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view){
        MainActivity.redirectActivity(this, InboxMgr.class);
    }
    public void ClickEditInbox(View view){
        MainActivity.redirectActivity(this,EditInbox.class);
    }

    //SETTINGS
    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,Settings.class);
    }

    public void ClickBackBtn3(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }


}
