package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button SearchBar;
    ImageView houseInfo;

    //for sidebar - show options by domain
    LinearLayout mainmenu, viewgrants, viewagentinfo, homecalc, mylistings, inbox, settings;
    TextView username;
    ImageView picture, picture1, picture2;

    //for login
    static String domain = "GENERAL";//= getDomain();
    boolean loggedIn = false;

    public static String getDomain() {
        return domain;
    }


    public static boolean setLoggedIn() { //set loggedIn value based on domain
        if (domain == "AGENT" || domain == "NON-AGENT") {
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //for login
//        domain = getDomain();  //set domain
        //MainActivity.domain = Login.getDomain();
        loggedIn = setLoggedIn();
        System.out.println("MainActivity domain and logged In: "+domain+loggedIn);

        readUserData(); //SELF CREATED FUNC database
        readAgentData(); //SELF CREATED FUNC database
        readHouseData(); //^

        //addListenerOnButton();
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
            username.setText("Monica Geller\nAgent ID: U273849K");
            picture1.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
        } else if (domain == "NON-AGENT") {  //for non-agents
            mylistings.setVisibility(View.GONE);
            username.setText("Rachel Green");
            picture2.setVisibility(VISIBLE);
            picture.setVisibility(View.GONE);
        } else {  //for general users
            mylistings.setVisibility(View.GONE);
            inbox.setVisibility(View.GONE);
        }


        //buttons in main activity
        SearchBar = findViewById(R.id.button);
        houseInfo = findViewById(R.id.imageView2);
        SearchBar.setOnClickListener(new View.OnClickListener() { //clicking on search bar
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_search_bar);
            }
        });

        houseInfo.setOnClickListener(new View.OnClickListener() { //clicking on house image
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_house_info);
            }
        });

    }


    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //when drawer is open - close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    //login permission popup
    public static void login(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Login Required");
        builder.setMessage("Please login to unlock feature");
        //login
        builder.setPositiveButton("Login/Sign Up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //activity.finishAffinity();
                //REDIRECT TO LOGIN PAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!
                redirectActivity(activity, Login.class);
            }
        });
//        //back
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    //camera permission popup
    public static void camera(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Permission Required");
        builder.setMessage("This app wants to have access to your Camera and Location.");
        //login
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //redirect to CAMERA!!!!!!!!!!!
                redirectActivity(activity, HomeCalculator.class);
            }
        });
//        //back
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }

    //SIDEBAR OPTIONS - FUNCTIONS
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    //MAIN MENU
    public void ClickHome(View view) {
        recreate();
    }

    public void ClickAdvFilters(View view) {
        redirectActivity(this, AdvancedFilters.class);
    }

    public void ClickCamera(View view) {
        camera(this);
    }

    public void ClickLogin(View view) {
        redirectActivity(this, Login.class);
    }

    ;

    //VIEW GRANT INFO
    public void ClickViewGrantsInfo(View view) {
        redirectActivity(this, ViewGrantsInfo.class);
    }

    public void ClickEligibility(View view) {
        if (loggedIn) {
            redirectActivity(this, ViewEligibility.class);
        } else {
            login(this);
        }
    }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view) {
        redirectActivity(this, ViewAgentInfo.class);
    }

    public void ClickAgent(View view) {
        redirectActivity(this, MonicaGeller.class);
    }

    //HOME CALC
    public void ClickHomeCalculator(View view) {
        //this code below is correct
        System.out.println("LOGGED IN:"+loggedIn);
        if (loggedIn) {
            redirectActivity(this, HomeCalculator.class);
        } else {
            login(this);
        }
    }

    //MY LISTINGS
    public void ClickMyListings(View view) {
        redirectActivity(this, MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view) {
        redirectActivity(this, Inbox.class);
    }

    public void ClickEditInbox(View view) {
        redirectActivity(this, EditInbox.class);
    }

    //SETTINGS
    public void ClickSettings(View view) {
        redirectActivity(this, Settings.class);
    }


    // SELF MADE FUNCTION
    private List<NonAgent> nonagent = new ArrayList<>();

    private void readUserData() {
        InputStream is = getResources().openRawResource(R.raw.nonagent); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                String[] tokens = line.split(",");

               NonAgent nonagents = new NonAgent();
                nonagents.setUserId(Integer.parseInt(tokens[0]));
                nonagents.setName(tokens[1]);
                nonagents.setAge(Integer.parseInt(tokens[2]));
                nonagents.setSalary(Integer.parseInt(tokens[3]));
                nonagents.setPassword(tokens[4]);
                nonagents.setDomain(tokens[5]);
                nonagents.setEmail(tokens[6]);
                nonagents.setNumber(tokens[7]);
                nonagents.setSecondbuyer_name(tokens[8]);
                nonagents.setSecondbuyer_age(tokens[9]);
                nonagents.setSecondbuyer_salary(Integer.parseInt(tokens[10]));
                nonagents.setRelationship(tokens[11]);
                nonagent.add(nonagents);
                Log.d("MyActivity", "Just Created: " + nonagents);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading on Line: " + line, e);
            e.printStackTrace();
        }

    }


    // SELF MADE FUNCTION
    private List<Agent> agent = new ArrayList<>();

    private void readAgentData() {
        InputStream iss = getResources().openRawResource(R.raw.agent); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(iss, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                String[] tokens = line.split(",");

                Agent agents = new Agent();
                agents.setUserId(Integer.parseInt(tokens[0]));
                agents.setCompName(tokens[1]);
                agents.setName(tokens[2]);
                agents.setPassword(tokens[3]);
                agents.setDomain(tokens[4]);
                agents.setEmail(tokens[5]);
                agents.setNumber(tokens[6]);
                agent.add(agents);
                Log.d("MyActivity", "Just Created: " + agents);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading on Line: " + line, e);
            e.printStackTrace();
        }


    }
    
    // SELF MADE FUNCTION
    private List<databass3> housedb = new ArrayList<>();

    private void readHouseData() {
        InputStream isss = getResources().openRawResource(R.raw.agent); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(isss, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                String[] tokens = line.split(",");

                databass3 houses = new databass3();
                houses.setAgent(Integer.parseInt(tokens[0]));
                houses.setMonth(tokens[1]);
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
                
                housedb.add(houses);
                Log.d("MyActivity", "Just Created: " + houses);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading on Line: " + line, e);
            e.printStackTrace();
        }


    }
