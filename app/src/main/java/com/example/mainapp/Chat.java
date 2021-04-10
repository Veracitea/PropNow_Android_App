package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Chat extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        button = findViewById(R.id.imageButton4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                             public boolean onMenuItemClick(MenuItem item) {
                                                 switch (item.getItemId()) {
                                                     case R.id.camera:
                                                         //Toast.makeText(Chat.this, "Keyword!", Toast.LENGTH_SHORT).show();
                                                         MainActivity.camera(Chat.this);
                                                         return true;
                                                     case R.id.photo:
                                                         // redirect to gallery
                                                         return true;
                                                     case R.id.doc:
                                                         // redirect to documents
                                                         return true;
                                                     case R.id.location:
                                                         // redirect to map
                                                         return true;
                                                     case R.id.contact:
                                                         // redirect to contacts
                                                         return true;
                                                     default:
                                                         return false;
                                                 }
                                             }
                                         });
    }


//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.camera:
//                System.out.println("CLICKED ON CAMERA");
//              //  MainActivity.camera(this);
//                MainActivity.redirectActivity(this,HomeCalculator.class);//change later!
//                return true;
////            case R.id.photo:
////                delete(item);
////                return true;
//            default:
//                recreate();
//                return false;
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);  // for the two icons in action bar
        return true;
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
        MainActivity.redirectActivity(this,ViewEligibility.class);
        //else{MainActivity.login(this);}
    }

    //VIEW AGENT INFO
    public void ClickViewAgentInfo(View view){ MainActivity.redirectActivity(this,ViewAgentInfo.class); }
    public void ClickAgent(View view){ MainActivity.redirectActivity(this,MonicaGeller.class); }

    //HOME CALC
    public void ClickHomeCalculator(View view){
        //this code below is correct
       MainActivity.redirectActivity(this,HomeCalculator.class);
       // else{MainActivity.login(this);}
    }

    //MY LISTINGS
    public void ClickMyListings(View view){
        MainActivity.redirectActivity(this,MyListings.class);
    }

    //INBOX
    public void ClickInbox(View view){
        recreate();
    }
    public void ClickEditInbox(View view){
        MainActivity.redirectActivity(this,EditInbox.class);
    }

    public void ClickChat(View view){
        MainActivity.redirectActivity(this, Chat.class);
    }

    //SETTINGS
    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,Settings.class);
    }
}