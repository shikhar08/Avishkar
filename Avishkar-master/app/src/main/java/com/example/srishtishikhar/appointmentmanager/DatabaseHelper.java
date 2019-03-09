package com.example.srishtishikhar.appointmentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.text.format.Time;

import com.google.android.gms.location.places.Place;

import java.util.concurrent.TimeoutException;

public class DatabaseHelper extends SQLiteOpenHelper {
  public static final String DATABASE_NAME = "Doctors.db";
  public static final String TABLE_NAME = "doctor_data";
  public static final String COL_1 ="LICENCE";
  public static final String COL_2 ="NAME";
  public static final String COL_3 ="SPECIALIZATION";
  public static final String COL_4 ="QUALIFICATIONS";
  public static final String COL_5 ="FEE";
  public static final String COL_6 ="ADDRESS";
  public static final String COL_7 ="PHONE_NO";
  public static final String COL_8 ="START_HOUR";
  public static final String COL_9 ="START_MINUTE";
  public static final String COL_10 ="END_HOUR";
  public static final String COL_11 ="END_MINUTE";
  public static final String COL_12 ="LATITUDE";
  public static final String COL_13="LONGITUDE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(" create table "+ TABLE_NAME + " (LICENCE TEXT PRIMARY KEY , NAME TEXT NOT NULL, SPECIALIZATION TEXT, QUALIFICATIONS TEXT, FEE INTEGER, ADDRESS TEXT, PHONE_NO TEXT, START_HOUR INTEGER, START_MINUTE INTEGER, END_HOUR INTEGER, END_MINUTE INTEGER, LATITUDE FLOAT, LONGITUDE FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(sqLiteDatabase);
    }

    public boolean InsertCommand(String ID, String name, String specialization, String qualifications, int fee, String address, String num, int sh,int sm , int eh , int em, float lat, float longi) {
      SQLiteDatabase db=this.getWritableDatabase();
      ContentValues contentValues=new ContentValues();
      contentValues.put(COL_1,ID);
      contentValues.put(COL_2,name);
      contentValues.put(COL_3,specialization);
      contentValues.put(COL_4,qualifications);
      contentValues.put(COL_5,fee);
      contentValues.put(COL_6,address);
      contentValues.put(COL_7,num);
      contentValues.put(COL_8, sh);
      contentValues.put(COL_9, sm);
      contentValues.put(COL_10, eh);
      contentValues.put(COL_11, em);
      contentValues.put(COL_12,lat);
      contentValues.put(COL_13,longi);
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
  public boolean updateData(String id, String name, String specialization, String qualifications, int fee, String address, String num, int sh,int sm , int eh , int em, float lat, float longi) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_1,id);
    contentValues.put(COL_2,name);
    contentValues.put(COL_3,specialization);
    contentValues.put(COL_4,qualifications);
    contentValues.put(COL_5,fee);
    contentValues.put(COL_6,address);
    contentValues.put(COL_7,num);
    contentValues.put(COL_8, sh);
    contentValues.put(COL_9, sm);
    contentValues.put(COL_10, eh);
    contentValues.put(COL_11, em);
    contentValues.put(COL_12,lat);
    contentValues.put(COL_13,longi);
    db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
    return true;
  }

  public Integer deleteData (String id) {
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
  }
}
