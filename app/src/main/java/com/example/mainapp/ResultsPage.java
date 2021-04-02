package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ResultsPage extends AppCompatActivity {

    ImageButton ARcamera;
    Button SearchBar;
    TextView filters;
    ImageButton filters2;
    ImageView houseInfo;
//    ToggleButton favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        ARcamera = findViewById(R.id.imageButton11);
        SearchBar = findViewById(R.id.button);
        filters = findViewById(R.id.textView3);
        filters2 = findViewById(R.id.imageButton3);
        houseInfo = findViewById(R.id.imageView);
//        favorites = findViewById(R.id.toggleButton2);

        ARcamera.setOnClickListener(new View.OnClickListener() { //clicking on AR camera button
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_a_r_search_permission);
            }
        });
        SearchBar.setOnClickListener(new View.OnClickListener() { //clicking on search bar
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_search_bar);
            }
        });
        filters.setOnClickListener(new View.OnClickListener() { //clicking on advanced filters (text)
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_advanced_filters);
            }
        });
        filters2.setOnClickListener(new View.OnClickListener() { //clicking on advanced filters(button)
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_advanced_filters);
            }
        });
        houseInfo.setOnClickListener(new View.OnClickListener() { //clicking on house image
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_house_info);
            }
        });
//        favorites.setOnClickListener(new View.OnClickListener() { //clicking on filter favorites
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_favorites);
//            }
//        });
    }

}