package com.example.srishtishikhar.appointmentmanager;

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
    public int starttime;

    public int endtime;

    public float latitude;
    public float longitude;

//    public Doctors(String new_name, String lic, String address, String spec, int fee, String qualifications, String phone){
//    }

    public Doctors(String names, String license_number, String address, String specialization, int fee, String qualification, String ph,int starttime,int endtime) {
        name = names;
        License_number = license_number;
        Address = address;
        Specialization = specialization;
        fees = fee;
        qualifications = qualification;
        phone = ph;
        this.starttime = starttime;
        this.endtime= endtime;
//        latitude = Float.parseFloat(a);
//        longitude = Float.parseFloat(b);
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

    public int getStarttime() {
        return starttime;
    }



    public int getEndtime() {
        return endtime;
    }


    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
