package com.example.rationalheads.vijay.grabdemo.databasehelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rationalheads.vijay.grabdemo.model.Contacts;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vijay on 19-04-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grabdemo2";
    private static final int DATABASE_VERSION = 1;
    private static final String COLL_EMAIL = "email";
    private static final String COLL_PASSWORD = "password";
    private static final String COLL_NAME = "name";
    private static final String COLL_NUMBER = "mobile";
    private static final String COLL_IMAGE = "image";
    private static final String COLL_ID = "id";
    private static final String TABLE_NAME = "demo1";
    private static final String USER_TABLE = "create table demo1(email text not null unique, password text not null, name text not null, mobile text not null);";
    SQLiteDatabase db;
    Context context;
    private String emailId, pass, name;
    // HashMap<String,String> al;


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_TABLE);
        this.db = db;


    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Contacts user) {

      db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, COLL_EMAIL + " = ?",
                new String[]{String.valueOf(user.getEmail())});
        db.close();
    }

    public void insertUser(Contacts c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLL_EMAIL, c.getEmail());
        values.put(COLL_PASSWORD, c.getPassword());
        values.put(COLL_NAME, c.getName());
        values.put(COLL_NUMBER, c.getMobile());
        //values.put(COLL_IMAGE,c.getImage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLL_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLL_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    public String searchUser(String email) {
//         al=new HashMap<>();
        db = this.getReadableDatabase();
        String query = "select email, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        pass = "not found";
        if (cursor.moveToFirst()) {
            do {
                emailId = cursor.getString(0);

                if (emailId.equals(email)) {
                    pass = cursor.getString(1);
                   // name = cursor.getString(2);
                    break;
                }

            } while (cursor.moveToNext());

        }
        return pass;
    }

    public String  getUserRecord(String  email){

        Cursor cursor1 = null;
        String empName = "";
        try {
            // array of columns to fetch
            String[] columns = {
                    COLL_NAME
            };
            db = this.getReadableDatabase();
            // selection criteria
            String selection = COLL_EMAIL + " = ?";
            // selection argument
            String[] selectionArgs = {email};
            cursor1 = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

            if(cursor1.getCount() > 0) {
                cursor1.moveToFirst();
                empName = cursor1.getString(cursor1.getColumnIndex("name"));
            }
            return empName;
        }finally {
            cursor1.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
        this.db = db;

    }
}
