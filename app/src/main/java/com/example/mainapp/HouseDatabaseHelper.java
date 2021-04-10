package com.example.mainapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HouseDatabaseHelper extends SQLiteOpenHelper {

    public static final String HOUSE_TABLE = "HOUSE_TABLE";
    public static final String COL_ID = "id";
    public static final String COL_MONTH = "month";
    public static final String COL_TOWN = "town";
    public static final String COL_FLAT_TYPE = "flat_type";
    public static final String COL_BLOCK = "block";
    public static final String COL_STREET_NAME = "street_name";
    public static final String COL_STOREY_RANGE = "storey_range";
    public static final String COL_FLOOR_AREA_SQM = "floor_area_sqm";
    public static final String COL_FLAT_MODEL = "flat_model";
    public static final String COL_LEASE_COMMENCE_DATE = "lease_commence_date";
    public static final String COL_REMAINING_LEASE = "remaining_lease";
    public static final String COL_RESALE_PRICE = "resale_price";
    public static final String COL_AGENT_NAME = "agent_name";

    public HouseDatabaseHelper(@Nullable Context context) {
        super(context, "House.db", null, 1);
    }

    //this is called the first time a database accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + HOUSE_TABLE + "(" + COL_ID + " INT  NOT NULL PRIMARY KEY AUTO_INCREMENT, " + COL_MONTH + " VARCHAR(7) NOT NULL," +
                COL_TOWN + " VARCHAR(15) NOT NULL , " + COL_FLAT_TYPE + " VARCHAR(16) NOT NULL, " + COL_BLOCK + " VARCHAR(4) NOT NULL, " + COL_STREET_NAME + " VARCHAR(20) NOT NULL," +
                COL_STOREY_RANGE + " VARCHAR(8) NOT NULL, " + COL_FLOOR_AREA_SQM + " NUMERIC(4,1) NOT NULL, " + COL_FLAT_MODEL + " VARCHAR(22) NOT NULL, " +
                COL_LEASE_COMMENCE_DATE + " INTEGER  NOT NULL, " + COL_REMAINING_LEASE + " VARCHAR(18) NOT NULL, " + COL_RESALE_PRICE + " NUMERIC(9,2) NOT NULL," +
                COL_AGENT_NAME + " VARCHAR(20) NOT NULL)";


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
