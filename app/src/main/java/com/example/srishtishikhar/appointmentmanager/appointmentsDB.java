package com.example.srishtishikhar.appointmentmanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class appointmentsDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Appointment.db";
    public static final String TABLE_NAME = "appointment_data";
    public static final String COL_1 ="DOCTOR_NAME";
    public static final String COL_2 ="DOCTOR_ID";
    public static final String COL_3 ="PATIENT_NAME";
    public static final String COL_4 ="PATIENT_NUM";
    public static final String COL_5 ="PATIENT_ID";
    public static final String COL_6 ="PATIENT_HOUR";
    public static final String COL_7 ="PATIENT_MINUTE";


    public appointmentsDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(" create table "+ TABLE_NAME + " (DOCTOR_NAME TEXT NOT NULL, DOCTOR_ID TEXT, PATIENT_NAME TEXT, PATIENT_NUM TEXT ,PATIENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PATIENT_HOUR INTEGER, PATIENT_MINUTE INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertCommand(String dn,String di,String pn,String pnum, int ph,int pm) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,dn);
        contentValues.put(COL_2,di);
        contentValues.put(COL_3,pn);
        contentValues.put(COL_4,pnum);
        contentValues.put(COL_6,ph);
        contentValues.put(COL_7,pm);

        long x=db.insert(TABLE_NAME,null,contentValues);
        if(x==-1)
            return false;
        else
            return true;
    }

    public boolean updateData(String dn,String di,String pn,String pnum,String pid,int ph, int pm) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,dn);
        contentValues.put(COL_2,di);
        contentValues.put(COL_3,pn);
        contentValues.put(COL_4,pnum);
        contentValues.put(COL_6,ph);
        contentValues.put(COL_7,pm);
        long x=db.insert(TABLE_NAME,null,contentValues);
        db.update(TABLE_NAME, contentValues, "PATIENT_ID = ?",new String[] {pid });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "PATIENT_ID = ?",new String[] {id});
    }

    public Cursor queryCommand(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT* FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor customQuery(String id) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ans = db.rawQuery("SELECT* FROM "+TABLE_NAME+" WHERE DOCTOR_ID = '"+id+"'",null);
        return ans;
    }


}
