package com.example.srishtishikhar.appointmentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PatientDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="patients.db";
    public static final String TABLE_NAME = "patient_data";
    public static final String COL_1 ="ID";
    public static final String COL_2="NAME";
    public static final String COL_3 ="GENDER";
    public static final String COL_4 ="AGE";
    public static final String COL_5 ="ADDRESS";
    public static final String COL_6 ="PHONE";
    public static final String COL_7 ="LATITUDE";
    public static final String COL_8 ="LONGITUDE";
    public static final String COL_9= "EMAIL";

    public PatientDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL , GENDER TEXT NOT NULL, AGE INTEGER, ADDRESS TEXT, PHONE TEXT, LATITUDE FLOAT, LONGITUDE FLOAT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertCommand(String name,String gender, int age,String address, String phone, float lat, float longi,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,gender);
        contentValues.put(COL_4,age);
        contentValues.put(COL_5,address);
        contentValues.put(COL_6,phone);
        contentValues.put(COL_7,lat);
        contentValues.put(COL_8,longi);
        contentValues.put(COL_9,email);
        long x=db.insert(TABLE_NAME,null,contentValues);
        if(x==-1)
            return false;
        else
            return true;
    }
    public Cursor queryCommand(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT* FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor customCommand(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ans = db.rawQuery("SELECT* FROM "+TABLE_NAME+" WHERE ID = '"+id+"'",null);
        return ans;
    }

}
