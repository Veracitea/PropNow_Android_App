package com.example.mainapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class houseInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
    }
    public void ClickBackBtn3(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }
}