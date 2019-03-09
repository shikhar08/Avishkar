package com.example.srishtishikhar.appointmentmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter<Doctors> {
    Context context;
    ArrayList<Doctors> objects;
    float c,d;
    public customAdapter(@NonNull Context context, @NonNull ArrayList<Doctors> objects, float lat, float longi) {
        super(context, 0, objects);
        this.context=context;
        this.objects=objects;
        c=lat;
        d=longi;
    }
    public double meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f/Math.PI);

        float a1 = lat_a / pk;
        float a2 = lng_a / pk;
        float b1 = lat_b / pk;
        float b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.e("answer",objects.get(0).name);
        View view=convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, parent, false);
        }

        Log.e("YE BHI",objects.get(position).name);

        Doctors doctors=getItem(position);
        float a=doctors.latitude;
        float b=doctors.longitude;
        double distance=meterDistanceBetweenPoints(a,b,c,d);
        distance = distance/1000;
        String val = String.valueOf(distance);
        Log.e("DISTANCE",""+distance);
        Log.e("distance",""+a+" "+b+" "+c+" "+d);

                TextView name=(TextView)view.findViewById(R.id.name_of_doctor);
        name.setText(doctors.name);

        TextView Ques =(TextView)view.findViewById(R.id.qualifications);
        Ques.setText(doctors.qualifications);

        TextView S=(TextView)view.findViewById(R.id.specialisation_of_doctor);
        S.setText(doctors.Specialization);

        TextView add=(TextView)view.findViewById(R.id.address);
        add.setText(doctors.Address);

        TextView starth=(TextView)view.findViewById(R.id.starthour);
        starth.setText(doctors.starthour+"");

        TextView startm=(TextView)view.findViewById(R.id.startminute);
        startm.setText(doctors.startminute+"");

        TextView endh=(TextView)view.findViewById(R.id.endhour);
        endh.setText(doctors.endhour+"");
        TextView endm=(TextView)view.findViewById(R.id.endminute);
        endm.setText(doctors.endminute+"");

        TextView m = (TextView)view.findViewById(R.id.distance);
        m.setText(val+" km");
        TextView h = (TextView) view.findViewById((R.id.ID_of_doctor));
        h.setText(doctors.License_number);
        return view;
    }
}
