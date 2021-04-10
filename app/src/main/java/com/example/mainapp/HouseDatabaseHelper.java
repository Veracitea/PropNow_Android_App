package com.example.mainapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HouseDatabaseHelper extends SQLiteOpenHelper {

    public static final String HOUSE_TABLE = "HOUSE_TABLE";

    public HouseDatabaseHelper(@Nullable Context context) {
        super(context, "House.db", null, 1);
    }

    //this is called the first time a database accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + HOUSE_TABLE + "()"


               /* CREATE TABLE HOUSE_TABLE (id INT  NOT NULL PRIMARY KEY AUTO_INCREMENT ,month VARCHAR(7) NOT NULL
                ,town                VARCHAR(15) NOT NULL
                ,flat_type           VARCHAR(16) NOT NULL
                ,block               VARCHAR(4) NOT NULL
                ,street_name         VARCHAR(20) NOT NULL
                ,storey_range        VARCHAR(8) NOT NULL
                ,floor_area_sqm      NUMERIC(4,1) NOT NULL
                ,flat_model          VARCHAR(22) NOT NULL
                ,lease_commence_date INTEGER  NOT NULL
                ,remaining_lease     VARCHAR(18) NOT NULL
                ,resale_price        NUMERIC(9,2) NOT NULL
        );*/

    }
//this is used if the version number changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
