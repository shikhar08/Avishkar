package com.example.srishtishikhar.appointmentmanager;

import android.location.Address;

/**
 * Created by Srishti Shikhar on 20-Sep-18.
 */

public class Doctors {
    public String name;
    public String License_number;
    public String Address;
    public String Specialization;
    public int fees;
    public String qualifications;
    public String phone;
    public int starthour;
    public int startminute;
    public int endhour;
    public int endminute;
    public float latitude;
    public float longitude;

    public Doctors(){
    }

    public Doctors(String names, String license_number, String address, String specialization, int fee, String qualification, String ph, int starth, int startm,int endh, int endm, float a, float b) {
        name = names;
        License_number = license_number;
        Address = address;
        Specialization = specialization;
        fees = fee;
        qualifications = qualification;
        phone = ph;
        starthour = starth;
        startminute= startm;
        endhour=endh;
        endminute=endm;
        latitude = a;
        longitude = b;
    }
    public String getName() {
        return name;
    }

    public String getLicense_number() {
        return License_number;
    }

    public String getAddress() {
        return Address;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public int getfees() {
        return fees;
    }

    public String getqualifications() {
        return qualifications;
    }

    public String getPhone() {
        return phone;
    }

    public int getStarthour() {
        return starthour;
    }

    public int getStartminute() {
        return startminute;
    }

    public int getEndhour() {
        return endhour;
    }

    public int getEndminute() {
        return endminute;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
