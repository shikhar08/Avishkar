package com.example.srishtishikhar.appointmentmanager;

public class Appointments {
    public String docName;
    public String docID;
    public String PatientName;
    public String Patmob;
    public String PatId;
    public int Hour;
    public int Minute;
    public Appointments(){ }

    public Appointments(String d,String i,String n,String m,String id, int hr,int min){
        docName = d;
        docID = i;
        PatientName = n;
        Patmob = m;
        PatId=id;
        Hour=hr;
        Minute= min;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocID() {
        return docID;
    }

    public String getPatId() {
        return PatId;
    }

    public int getHour() {
        return Hour;
    }

    public int getMinute() {
        return Minute;
    }

    public String getPatientName() {
        return PatientName;
    }

    public String getPatmob() {
        return Patmob;
    }


}
