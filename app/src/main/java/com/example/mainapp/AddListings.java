package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class AddListings extends AppCompatActivity {

    //references to buttons and other controls on layout
    Button btn_addListing;
    EditText et_blockNum, et_unitNum, et_street, et_bedroomNum, et_nearestMRT;
    ListView lv_houseList;

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


        btn_addListing = findViewById(R.id.btn_addListing);
        et_blockNum = findViewById(R.id.et_blockNum);
        et_unitNum = findViewById(R.id.et_unitNum);
        et_street = findViewById(R.id.et_street);
        et_bedroomNum = findViewById(R.id.et_bedroomNum);
        et_nearestMRT = findViewById(R.id.et_nearestMRT);

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

        //button listeners for the add and view all buttons
        btn_addListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    House house = new House(-1,
                            Integer.parseInt(et_bedroomNum.getText().toString()),
                            et_blockNum.toString(),
                            et_unitNum.toString(),
                            et_nearestMRT.toString());


                    Toast.makeText(AddListings.this , house.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(AddListings.this , "error adding house", Toast.LENGTH_SHORT).show();

                }
                
                
            }
        });






    }
}