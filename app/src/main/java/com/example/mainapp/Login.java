package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import static java.lang.Boolean.FALSE;

public class Login extends AppCompatActivity {
    android.widget.Spinner spinner;
    private EditText Username,Password;
    private Button Login;
    private TextView Info;
    private int counter = 5;
    List<NonAgent> nonAgentList = new ArrayList<>();
    List<Agent> AgentList = new ArrayList<>();

    DrawerLayout drawerLayout;
    //getting domain and loggedIn status
    static String domain; //= "GENERAL"; //set to general first

    //for sidebar - show options by domain
    LinearLayout mainmenu,viewgrants,viewagentinfo,homecalc,mylistings,inbox,settings;
    TextView username;
    ImageView picture,picture1,picture2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        drawerLayout = findViewById(R.id.drawer_layout);
        spinner = findViewById(R.id.spinner1);
        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Info = (TextView)findViewById(R.id.tvInfo);

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

        //  spinner
        String[] items = new String[]{"Agent","Non-Agent"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        readAgent();
        readNonAgent();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString(),spinner.getSelectedItem().toString());
            }
        });
    }

    private void setDomain(String domain){
        com.example.mainapp.Login.domain = domain;
    }

    public static String getDomain(){
        return com.example.mainapp.Login.domain;
    }

    boolean loggedIn = MainActivity.setLoggedIn();

    protected void validate(String userName, String userPassword,String userDomain) {
        String username;
        String password;
        boolean correct = false;

        if ((userDomain.equals("Agent"))) {
            for(Agent a: AgentList){
                username = a.getUsername();
                password = a.getPassword();
                if((userName.equals(username))&&(userPassword.equals(password))){
                    setDomain("AGENT");
                    MainActivity.domain = "AGENT";
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    correct = true;
                    break;
                }
            }
        }
        else{
            if ((userDomain.equals("Non-Agent"))) {
                for (NonAgent n : nonAgentList) {
                    username = n.getName();
                    password = n.getPassword();
                    if ((userName.equals(username)) && (userPassword.equals(password))) {
                        setDomain("NON-AGENT");
                        MainActivity.domain = "NON-AGENT";
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        correct = true;
                        break;
                    }
                }
            }
        }

        if (correct == false){
            counter--;
            Username.setText("");
            Password.setText("");
            Info.setText("Number of attempt remaining: " + counter);
            Toast.makeText(Login.this, "Invalid Credentials! Please try again", Toast.LENGTH_SHORT).show();

            if(counter == 0) {
                Login.setEnabled(false);
                setDomain("GENERAL");
                MainActivity.domain = "GENERAL";
                //print out - ure not authorized to login?
                Toast.makeText(Login.this, "You are not authorized to login!", Toast.LENGTH_SHORT).show();
            }

        }
        domain = "GENERAL";
        setDomain("GENERAL");
    }

    private void readNonAgent(){
        InputStream is = getResources().openRawResource(R.raw.nonagent); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line= "";
        try{
            reader.readLine();
            while((line=reader.readLine())!= null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens= line.split(",");

                NonAgent nonagents= new NonAgent();
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
                nonAgentList.add(nonagents);
                Log.d("MyActivity","Just Created: " + nonagents);
            }
        }catch (IOException e){
            Log.wtf("MyActivity","Error reading on Line: " + line,e);
            e.printStackTrace();
        }
    }

    private void readAgent(){
        InputStream is = getResources().openRawResource(R.raw.agent); //imp class
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")) //alt enter and import class charset
        );

        String line= "";
        try{
            reader.readLine();
            while((line=reader.readLine())!= null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens= line.split(",");

                Agent agents= new Agent();
                agents.setUserId(Integer.parseInt(tokens[0]));
                agents.setCompany(tokens[1]);
                agents.setUsername(tokens[2]);
                agents.setPassword(tokens[3]);
                agents.setEmail(tokens[4]);
                agents.setNumber(tokens[5]);
                AgentList.add(agents);
                Log.d("MyActivity","Just Created: " + agents);
            }
        }catch (IOException e){
            Log.wtf("MyActivity","Error reading on Line: " + line,e);
            e.printStackTrace();
        }
    }





    //for layout menu:
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