package com.example.mainapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AgentDatabaseHelper extends SQLiteOpenHelper {

    public static final String AGENT_TABLE = "AGENT_TABLE";
    public static final String COL_ID = "id";
    public static final String COL_COMPANY = "company";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_DOMAIN = "domain";
    public static final String COL_EMAIL = "email";
    public static final String COL_NUMBER = "number";

    public AgentDatabaseHelper(@Nullable Context context) {
        super(context, "Agent.db", null, 1);
    }

    //this is called the first time a database accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + AGENT_TABLE + "(" + COL_ID + " INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COL_COMPANY + " VARCHAR(20) NOT NULL , " + COL_USERNAME + " VARCHAR(20) NOT NULL, " + COL_PASSWORD + " VARCHAR(30) NOT NULL, " + COL_DOMAIN + " VARCHAR(20) NOT NULL," +
                COL_EMAIL + " VARCHAR(20) NOT NULL, " + COL_NUMBER + " VARCHAR(20) NOT NULL)";

        db.execSQL(createTableStatement);


    }

    //this is used if the version number changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Agent agent) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COMPANY, agent.getCompany());
        cv.put(COL_USERNAME, agent.getUsername());
        cv.put(COL_PASSWORD, agent.getPassword());
        cv.put(COL_DOMAIN, agent.getDomain());
        cv.put(COL_EMAIL, agent.getEmail());
        cv.put(COL_NUMBER, agent.getNumber());


        long insert = db.insert(AGENT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {

            return true;
        }
    }

    public boolean DeleteOne(Agent agent){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + AGENT_TABLE + " WHERE " + COL_ID + " = " + agent.getUserId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }


    }



    public List<Agent> getAll(){
        List<Agent> returnList = new ArrayList<>();
        //get data from the database
        String queryString = "SELECT * FROM " + AGENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            //loop through cursor(result set) and get new house objects. Put them into result list.
            do{
                int agentID = cursor.getInt(0);
                String company = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                String domain = cursor.getString(4);
                String email = cursor.getString(5);
                String number = cursor.getString(6);

                Agent newListing = new Agent(agentID, company, username, password,domain,email,number);
                returnList.add(newListing);
            }
            while(cursor.moveToNext());

        }
        else{
            //failure. do not add anything to the list

        }
        //close both cursor and db when done
        cursor.close();
        db.close();
        return returnList;
    }


}
